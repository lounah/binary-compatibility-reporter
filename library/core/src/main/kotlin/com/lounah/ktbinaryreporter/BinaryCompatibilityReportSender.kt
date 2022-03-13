package com.lounah.ktbinaryreporter

public interface BinaryCompatibilityReportSender {

    public suspend fun send(verdict: Verdict)

    public object Verbose : BinaryCompatibilityReportSender {

        override suspend fun send(verdict: Verdict) {
            println(verdict)
        }
    }
}
