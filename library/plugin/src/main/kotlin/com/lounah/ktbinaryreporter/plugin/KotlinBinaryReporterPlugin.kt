package com.lounah.ktbinaryreporter.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.FileCollection

class KotlinBinaryReporterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = KotlinBinaryReporterExtension.get(project)
        project.tasks
            .register("checkBinaryCompatibility", CheckBinaryCompatibilityTask::class.java)
            .configure {
                currentABI = project.createCurrentABIConfiguration()
                previousABI = project.createPreviousABIConfiguration(extension.abiVersion)
            }
    }

    private fun Project.createCurrentABIConfiguration(): FileCollection {
        val dependency = dependencies.create(project)
        return createJarConfiguration("currentABI", dependency)
    }

    private fun Project.createPreviousABIConfiguration(version: String): FileCollection? {
        val dependency = dependencies.create("$group:$name:$version")
        val configuration = createJarConfiguration("previousABI", dependency)
        return runCatching { configuration.copy().resolve() }
            .map { configuration }
            .getOrNull()
    }

    private fun Project.createJarConfiguration(name: String, defaultDependency: Dependency): Configuration {
        return configurations.maybeCreate(name).apply {
            isCanBeResolved = true
            isCanBeConsumed = false
            isTransitive = false
            attributes { attribute(Attribute.of("artifactType", String::class.java), "jar") }
            defaultDependencies { add(defaultDependency) }
        }
    }
}
