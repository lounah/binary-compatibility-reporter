package com.lounah.ktbinaryreporter.plugin

import com.lounah.ktbinaryreporter.plugin.internal.di.KotlinBinaryReporterComponent
import kotlinx.coroutines.*
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

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

        runBlocking {
            launch(CoroutineName("report-binary-compatibility-${project.name}")) {
                component.binaryCompatibilityChecker.check(previousClasses, newClasses)
                    .run { component.binaryCompatibilityReportSender.send(this) }
            }
        }
    }
}
