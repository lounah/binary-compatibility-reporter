package com.lounah.ktbinaryreporter.plugin

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

class KotlinBinaryReporterExtension(
    var abiVersion: String = "",
    var reporter: ReportSettings = ReportSettings(),
    var ignoredPackages: Set<String> = emptySet(),
    var ignoredClasses: Set<String> = emptySet()
) {

    fun reporter(block: ReportSettings.() -> Unit) {
        reporter = ReportSettings().also(block)
    }

    companion object {

        fun get(project: Project): KotlinBinaryReporterExtension {
            return project.extensions.getByType()
        }
    }
}

class ReportSettings(
    var credentials: GitCredentials = GitCredentials.None,
    var reportTarget: ReportTarget = ReportTarget.None
) {

    fun gitlab(block: ReportTarget.Gitlab.() -> Unit) {
        reportTarget = ReportTarget.Gitlab().also(block)
    }

    fun github(block: ReportTarget.Github.() -> Unit) {
        reportTarget = ReportTarget.Github().also(block)
    }

    fun bitbucket(block: ReportTarget.Bitbucket.() -> Unit) {
        reportTarget = ReportTarget.Bitbucket().also(block)
    }
}

sealed class ReportTarget {

    class Gitlab(
        val projectId: String = "",
        val mrId: String = "",
        val baseUrl: String = ""
    ) : ReportTarget()

    class Github() : ReportTarget()

    class Bitbucket() : ReportTarget()

    object None : ReportTarget()
}

sealed class GitCredentials {

    class Basic(val username: String, val password: String) : GitCredentials()

    class Bearer(val token: Provider<String>) : GitCredentials()

    class Base64(val credentials: String) : GitCredentials()

    object None : GitCredentials()
}
