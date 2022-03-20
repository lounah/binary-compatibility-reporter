package com.lounah.ktbinaryreporter.plugin

import com.lounah.ktbinaryreporter.api.Credentials
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

open class KotlinBinaryReporterExtension(
    var abiVersion: String = "latest",
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
    var credentials: Credentials = Credentials.None,
    var reportTarget: ReportTarget = ReportTarget.Verbose,
    var enableLogging: Boolean = false
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
        var projectId: String = "",
        var mrId: String = "",
        var baseUrl: String = ""
    ) : ReportTarget()

    class Github(
        var baseUrl: String = "",
        var owner: String = "",
        var repo: String = "",
        var pullId: String = ""
    ) : ReportTarget()

    class Bitbucket(
        var baseUrl: String = "",
        var projectKey: String = "",
        var repositorySlug: String = "",
        var pullRequestId: String = ""
    ) : ReportTarget()

    object Verbose : ReportTarget()
}
