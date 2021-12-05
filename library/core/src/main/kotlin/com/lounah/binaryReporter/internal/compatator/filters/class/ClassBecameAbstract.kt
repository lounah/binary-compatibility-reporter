package com.lounah.binaryReporter.internal.compatator.filters.`class`

import com.lounah.binaryReporter.BinaryCompatibilityViolation
import com.lounah.binaryReporter.BinaryCompatibilityViolation.ClassSignature
import com.lounah.binaryReporter.BinaryCompatibilityViolation.Empty
import com.lounah.binaryReporter.BinarySignatureComparator.ViolationRule
import com.lounah.binaryReporter.internal.compatator.util.isAbstract
import kotlinx.validation.api.ClassBinarySignature

internal class ClassBecameAbstract : ViolationRule {

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
        return if (!oldClass.access.isAbstract && newClass.access.isAbstract) {
            ClassSignature(oldClass, newClass, getDescription(oldClass))
        } else {
            Empty
        }
    }

    private fun getDescription(oldClass: ClassBinarySignature): String {
        return "Non-abstract class '${oldClass.name}' became abstract."
    }
}