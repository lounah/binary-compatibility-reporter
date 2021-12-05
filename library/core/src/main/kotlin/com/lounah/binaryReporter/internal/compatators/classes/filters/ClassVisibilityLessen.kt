package com.lounah.binaryReporter.internal.compatators.classes.filters

import com.lounah.binaryReporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassVisibilityLessen : ViolationRule {

    override fun getDescription(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Class '${oldClass?.name}' visibility lessen. " +
                "Was: '${oldClass?.access?.getModifierString()}', " +
                "now: ${newClass?.access?.getModifierString()}"
    }

    override fun matches(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Boolean {
        return when {
            oldClass == null && newClass == null -> false
            oldClass == null && newClass != null -> false
            oldClass != null && newClass != null -> {
                oldClass.access.isPublic && !newClass.access.isPublic
            }
            else -> false
        }
    }
}
