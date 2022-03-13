package com.lounah.ktbinaryreporter.internal

import com.lounah.ktbinaryreporter.BinaryCompatibilityChecker
import com.lounah.ktbinaryreporter.BinarySignatureComparator
import com.lounah.ktbinaryreporter.Verdict
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.filterOutNonPublic
import kotlinx.validation.api.loadApiFromJvmClasses
import java.util.jar.JarFile

internal class KotlinBinaryCompatibilityChecker(
    private val classesComparator: BinarySignatureComparator,
    private val methodsComparator: BinarySignatureComparator,
    private val fieldsComparator: BinarySignatureComparator
) : BinaryCompatibilityChecker {

    override fun check(oldClasses: List<ClassBinarySignature>, newClasses: List<ClassBinarySignature>): Verdict {
        val binaryCompatibilityViolations = oldClasses
            .associateByClassName(newClasses)
            .flatMap { (oldClass, newClass) ->
                classesComparator.compare(oldClass, newClass)
                    .plus(methodsComparator.compare(oldClass, newClass))
                    .plus(fieldsComparator.compare(oldClass, newClass))
            }

        return Verdict(binaryCompatibilityViolations)
    }

    private fun List<ClassBinarySignature>.associateByClassName(
        otherApi: List<ClassBinarySignature>
    ): Map<ClassBinarySignature, ClassBinarySignature?> {
        return associateWith { classSignature ->
            otherApi.firstOrNull { classSignature.name == it.name }
        }
    }
}
