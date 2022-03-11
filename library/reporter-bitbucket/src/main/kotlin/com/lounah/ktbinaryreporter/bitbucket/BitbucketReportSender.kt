package com.lounah.ktbinaryreporter.bitbucket

import com.lounah.ktbinaryreporter.Verdict
import com.lounah.ktbinaryreporter.api.BinaryCompatibilityReportSender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class BitbucketReportSender(
    private val api: BitbucketApi,
    private val projectKey: String,
    private val repositorySlug: String,
    private val pullRequestId: String,
) : BinaryCompatibilityReportSender {

    override suspend fun send(verdict: Verdict) {
        return withContext(Dispatchers.IO) {
            api.sendComment(projectKey, repositorySlug, pullRequestId, verdict.asBitbucketComment())
        }
    }

    private fun Verdict.asBitbucketComment(): Comment {
        return Comment(this.toString())
    }
}