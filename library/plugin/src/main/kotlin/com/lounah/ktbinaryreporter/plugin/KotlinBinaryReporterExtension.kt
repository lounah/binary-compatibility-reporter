package com.lounah.ktbinaryreporter.plugin

import com.lounah.ktbinaryreporter.api.Credentials
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
        val projectId: String = "",
        val mrId: String = "",
        val baseUrl: String = ""
    ) : ReportTarget()

    class Github(
        val baseUrl: String = "",
        val owner: String = "",
        val repo: String = "",
        val pullId: String = ""
    ) : ReportTarget()

    class Bitbucket(
        val baseUrl: String = "",
        val projectKey: String = "",
        val repositorySlug: String = "",
        val pullRequestId: String = ""
    ) : ReportTarget()

    object Verbose : ReportTarget()
}
