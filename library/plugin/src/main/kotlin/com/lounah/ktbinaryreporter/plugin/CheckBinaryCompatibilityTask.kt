package com.lounah.ktbinaryreporter.plugin

import com.lounah.ktbinaryreporter.plugin.internal.di.KotlinBinaryReporterComponent
import kotlinx.coroutines.*
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.*

abstract class CheckBinaryCompatibilityTask : DefaultTask() {

    @get:Optional
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    abstract var previousABI: FileCollection?

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    abstract var currentABI: FileCollection

    @TaskAction
    fun run() {
        if (previousABI == null) {
            logger.info("Could not resolve previous api. Skipping task.")
            return
        }

        val component = KotlinBinaryReporterComponent(project)
        val previousClasses = component.extractClasses(previousABI!!)
        val newClasses = component.extractClasses(currentABI)

        runBlocking {
            launch(CoroutineName("report-binary-compatibility-${project.name}")) {
                component.binaryCompatibilityChecker.check(previousClasses, newClasses)
                    .run { component.binaryCompatibilityReportSender.send(this) }
            }
        }
    }
}
