package com.lounah.binaryReporter.internal.compatator.filters.`class`

import com.lounah.binaryReporter.BinaryCompatibilityViolation
import com.lounah.binaryReporter.BinaryCompatibilityViolation.ClassSignature
import com.lounah.binaryReporter.BinaryCompatibilityViolation.Empty
import com.lounah.binaryReporter.BinarySignatureComparator.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassNameChanged : ViolationRule {

    override fun check(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): BinaryCompatibilityViolation {
        return when {
            oldClass == null && newClass == null -> Empty
            oldClass == null && newClass != null -> Empty
            oldClass != null && newClass != null -> checkInternal(oldClass, newClass)
            else -> Empty
        }
    }

    private fun checkInternal(
        oldClass: ClassBinarySignature,
        newClass: ClassBinarySignature
    ): BinaryCompatibilityViolation {
        return if (oldClass.name != newClass.name) {
            ClassSignature(oldClass, newClass, getDescription(oldClass))
        } else {
            Empty
        }
    }

    private fun getDescription(oldClass: ClassBinarySignature): String {
        return "Class '${oldClass.name}' was renamed or deleted."
    }
}
