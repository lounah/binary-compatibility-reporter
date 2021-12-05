package com.lounah.binaryReporter.internal

import com.lounah.binaryReporter.BinaryCompatibilityChecker
import com.lounah.binaryReporter.BinarySignatureComparator
import com.lounah.binaryReporter.Verdict
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.filterOutNonPublic
import kotlinx.validation.api.loadApiFromJvmClasses
import java.util.jar.JarFile

internal class KotlinBinaryCompatibilityChecker(
    private val ignoredPackages: Set<String>,
    private val ignoredClasses: Set<String>,
    private val classesComparator: BinarySignatureComparator,
    private val methodsComparator: BinarySignatureComparator,
    private val fieldsComparator: BinarySignatureComparator
) : BinaryCompatibilityChecker {

    override fun check(oldClasses: JarFile, newClasses: JarFile): Verdict {
        val binaryCompatibilityViolations = oldClasses.extractPublicApi()
            .associateByClassName(newClasses.extractPublicApi())
            .flatMap { (oldClass, newClass) ->
                classesComparator.compare(oldClass, newClass)
                    .plus(methodsComparator.compare(oldClass, newClass))
                    .plus(fieldsComparator.compare(oldClass, newClass))
            }

        return Verdict(binaryCompatibilityViolations)
    }

    private fun JarFile.extractPublicApi(): List<ClassBinarySignature> {
        return loadApiFromJvmClasses()
            .filterOutNonPublic(ignoredPackages, ignoredClasses)
    }

    private fun List<ClassBinarySignature>.associateByClassName(
        otherApi: List<ClassBinarySignature>
    ): Map<ClassBinarySignature, ClassBinarySignature?> {
        return associateWith { classSignature ->
            otherApi.firstOrNull { classSignature.name == it.name }
        }
    }
}
