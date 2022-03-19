package com.lounah.ktbinaryreporter.gitlab

import com.lounah.ktbinaryreporter.Verdict
import com.lounah.ktbinaryreporter.BinaryCompatibilityReportSender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class GitlabReportSender(
    private val api: GitlabApi,
    private val projectId: String,
    private val mrId: String
) : BinaryCompatibilityReportSender {

    override suspend fun send(verdict: Verdict) {
        return withContext(Dispatchers.IO) {
            api.sendComment(projectId, mrId, verdict.toString())
        }
    }
}
