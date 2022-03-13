package com.lounah.ktbinaryreporter.plugin

import com.lounah.ktbinaryreporter.plugin.internal.di.KotlinBinaryReporterComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.impldep.jakarta.xml.bind.JAXBElement

abstract class CheckBinaryCompatibilityTask : DefaultTask() {

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    abstract var previousABI: FileCollection?

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    abstract var currentABI: FileCollection

    @TaskAction
    fun run() {
        requireNotNull(previousABI) { logger.info("Could not resolve previous api. Skipping task.") }
        val component = KotlinBinaryReporterComponent(project)
        val previousClasses = component.extractClasses(previousABI!!)
        val newClasses = component.extractClasses(currentABI)

        CoroutineScope(Dispatchers.IO).launch {
            component.binaryCompatibilityChecker.check(previousClasses, newClasses)
                .run { component.binaryCompatibilityReportSender.send(this) }
        }
    }
}
