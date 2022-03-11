package com.lounah.ktbinaryreporter.plugin

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
        if (previousABI == null) {
            logger.info("Could not resolve previous api. Skipping task.")
        } else {

        }
    }
}
