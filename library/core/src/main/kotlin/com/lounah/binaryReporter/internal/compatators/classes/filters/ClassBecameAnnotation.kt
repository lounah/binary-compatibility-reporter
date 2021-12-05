package com.lounah.binaryReporter.internal.compatators.classes.filters

import com.lounah.binaryReporter.ViolationRule
import com.lounah.binaryReporter.internal.compatators.util.isAnnotation
import kotlinx.validation.api.ClassBinarySignature

internal class ClassBecameAnnotation : ViolationRule {

    override fun getDescription(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Class '${oldClass?.name}' became annotation."
    }

    override fun matches(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Boolean {
        return when {
            oldClass == null && newClass == null -> false
            oldClass == null && newClass != null -> false
            oldClass != null && newClass != null -> {
                !oldClass.access.isAnnotation && newClass.access.isAnnotation
            }
            else -> false
        }
    }
}
