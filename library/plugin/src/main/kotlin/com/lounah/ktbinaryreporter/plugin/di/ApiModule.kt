package com.lounah.ktbinaryreporter.plugin.di

import com.lounah.ktbinaryreporter.BinaryCompatibilityReportSender

internal interface ApiModule {

    val reportSender: BinaryCompatibilityReportSender
}

internal fun ApiModule(): ApiModule {

}