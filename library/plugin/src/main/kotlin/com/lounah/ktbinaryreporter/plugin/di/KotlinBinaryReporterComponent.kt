package com.lounah.ktbinaryreporter.plugin.di

import com.lounah.ktbinaryreporter.BinaryCompatibilityChecker
import com.lounah.ktbinaryreporter.api.BinaryCompatibilityReportSender
import org.gradle.api.Project

internal interface KotlinBinaryReporterComponent {

    val binaryCompatibilityChecker: BinaryCompatibilityChecker
    val reportSender: BinaryCompatibilityReportSender
}

internal fun KotlinBinaryReporterComponent(project: Project): KotlinBinaryReporterComponent {
    return object : KotlinBinaryReporterComponent, ApiModule by ApiModule() {

        override val binaryCompatibilityChecker: BinaryCompatibilityChecker
            get() = BinaryCompatibilityChecker()
    }
}
