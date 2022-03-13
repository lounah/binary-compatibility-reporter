package com.lounah.ktbinaryreporter.plugin.internal.di

import com.lounah.ktbinaryreporter.BinaryCompatibilityChecker
import com.lounah.ktbinaryreporter.BinaryCompatibilityReportSender
import com.lounah.ktbinaryreporter.plugin.KotlinBinaryReporterExtension
import com.lounah.ktbinaryreporter.plugin.internal.ReporterFactory
import com.lounah.ktbinaryreporter.plugin.internal.abi.ClassesExtractor
import org.gradle.api.Project

internal interface KotlinBinaryReporterComponent {

    val extractClasses: ClassesExtractor.Composite
    val binaryCompatibilityChecker: BinaryCompatibilityChecker
    val binaryCompatibilityReportSender: BinaryCompatibilityReportSender
}

internal fun KotlinBinaryReporterComponent(project: Project): KotlinBinaryReporterComponent {
    return object : KotlinBinaryReporterComponent {

        private val extension = KotlinBinaryReporterExtension.get(project)

        private val createReportSender
            get() = ReporterFactory(extension.reporter.credentials, extension.reporter.enableLogging)

        override val binaryCompatibilityChecker: BinaryCompatibilityChecker
            get() = BinaryCompatibilityChecker()

        override val binaryCompatibilityReportSender: BinaryCompatibilityReportSender
            get() = createReportSender(extension.reporter.reportTarget)

        override val extractClasses: ClassesExtractor.Composite
            get() = ClassesExtractor.Composite(
                ClassesExtractor.FileCollectionExtractor(),
                ClassesExtractor.JarFileExtractor(),
                extension.ignoredPackages,
                extension.ignoredClasses
            )
    }
}
