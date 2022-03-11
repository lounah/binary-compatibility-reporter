package com.lounah.ktbinaryreporter.api

import com.lounah.ktbinaryreporter.Verdict

public interface BinaryCompatibilityReportSender {

    public suspend fun send(verdict: Verdict)
}
