package com.lounah.ktbinaryreporter.plugin.di

import com.lounah.ktbinaryreporter.api.BinaryCompatibilityReportSender

internal interface ApiModule {

    val reportSender: BinaryCompatibilityReportSender
}

internal fun ApiModule(): ApiModule {

}