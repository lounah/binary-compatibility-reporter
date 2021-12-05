package com.lounah.binaryReporter.internal.compatator.filters.`class`

import com.lounah.binaryReporter.BinaryCompatibilityViolation
import com.lounah.binaryReporter.BinaryCompatibilityViolation.*
import com.lounah.binaryReporter.BinarySignatureComparator.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassSupertypesChanged : ViolationRule {

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
        return if (oldClass.supertypes != newClass.supertypes) {
            ClassSignature(oldClass, newClass, getDescription(oldClass, newClass))
        } else {
            Empty
        }
    }

    private fun getDescription(
        oldClass: ClassBinarySignature,
        newClass: ClassBinarySignature
    ): String {
        return "Class '${oldClass.name}' supertypes changed. " +
                "Was: '${oldClass.supertypes.joinToString()}'; " +
                "now: ${newClass.supertypes.joinToString()}"
    }
}
