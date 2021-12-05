package com.lounah.binaryReporter.internal.compatator.filters.`class`

import com.lounah.binaryReporter.BinaryCompatibilityViolation
import com.lounah.binaryReporter.BinaryCompatibilityViolation.ClassSignature
import com.lounah.binaryReporter.BinaryCompatibilityViolation.Empty
import com.lounah.binaryReporter.BinarySignatureComparator.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassVisibilityLessen : ViolationRule {

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
        return if (oldClass.access.isPublic && !newClass.access.isPublic) {
            ClassSignature(oldClass, newClass, getDescription(oldClass, newClass))
        } else {
            Empty
        }
    }

    private fun getDescription(
        oldClass: ClassBinarySignature,
        newClass: ClassBinarySignature
    ): String {
        return "Class '${oldClass.name}' visibility lessen. " +
                "Was: '${oldClass.access.getModifierString()}', " +
                "now: ${newClass.access.getModifierString()}"
    }
}
