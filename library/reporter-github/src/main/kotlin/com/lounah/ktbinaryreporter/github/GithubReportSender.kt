package com.lounah.ktbinaryreporter.github

import com.lounah.ktbinaryreporter.Verdict
import com.lounah.ktbinaryreporter.BinaryCompatibilityReportSender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class GithubReportSender(
    private val api: GithubApi,
    private val owner: String,
    private val repo: String,
    private val pullId: String
) : BinaryCompatibilityReportSender {

    override suspend fun send(verdict: Verdict) {
        return withContext(Dispatchers.IO) {
            api.sendComment(owner, repo, pullId, verdict.toString())
        }
    }
}
