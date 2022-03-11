package com.lounah.ktbinaryreporter.api

import com.lounah.ktbinaryreporter.Verdict

public interface BinaryCompatibilityReportSender {

    public fun send(verdict: Verdict)
}
