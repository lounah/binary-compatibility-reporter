package com.lounah.ktbinaryreporter

public interface BinaryCompatibilityReportSender {

    public suspend fun send(verdict: Verdict)
}
