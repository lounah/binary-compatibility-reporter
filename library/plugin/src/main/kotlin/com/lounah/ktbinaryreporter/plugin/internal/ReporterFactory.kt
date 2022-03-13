package com.lounah.ktbinaryreporter.plugin.internal

import com.lounah.ktbinaryreporter.BinaryCompatibilityReportSender
import com.lounah.ktbinaryreporter.api.Credentials
import com.lounah.ktbinaryreporter.bitbucket.BitbucketApi
import com.lounah.ktbinaryreporter.bitbucket.BitbucketReportSender
import com.lounah.ktbinaryreporter.github.GithubApi
import com.lounah.ktbinaryreporter.github.GithubReportSender
import com.lounah.ktbinaryreporter.gitlab.GitlabApi
import com.lounah.ktbinaryreporter.gitlab.GitlabReportSender
import com.lounah.ktbinaryreporter.plugin.ReportTarget
import com.lounah.ktbinaryreporter.plugin.ReportTarget.*

internal class ReporterFactory(
    private val credentials: Credentials,
    private val enableLogging: Boolean
) : (ReportTarget) -> BinaryCompatibilityReportSender {

    override fun invoke(target: ReportTarget): BinaryCompatibilityReportSender { 
        return when(target) {
            is Gitlab -> target.createGitlabReportSender()
            is Github -> target.createGithubReportSender()
            is Bitbucket -> target.createBitbucketReportSender()
            else -> BinaryCompatibilityReportSender.Verbose
        }
    }

    private fun Gitlab.createGitlabReportSender(): BinaryCompatibilityReportSender {
        return GitlabReportSender(
            api = GitlabApi.create(baseUrl, credentials, enableLogging),
            projectId = projectId,
            mrId = mrId
        )
    }

    private fun Github.createGithubReportSender(): BinaryCompatibilityReportSender {
        return GithubReportSender(
            api = GithubApi.create(baseUrl, credentials, enableLogging),
            owner = owner,
            repo = repo,
            pullId = pullId
        )
    }

    private fun Bitbucket.createBitbucketReportSender(): BinaryCompatibilityReportSender {
        return BitbucketReportSender(
            api = BitbucketApi.create(baseUrl, credentials, enableLogging),
            projectKey = projectKey,
            repositorySlug = repositorySlug,
            pullRequestId = pullRequestId
        )
    }
}