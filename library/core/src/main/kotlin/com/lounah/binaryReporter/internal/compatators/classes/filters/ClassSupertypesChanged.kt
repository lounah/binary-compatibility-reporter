package com.lounah.binaryReporter.internal.compatators.classes.filters

import com.lounah.binaryReporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassSupertypesChanged : ViolationRule {

    override fun getDescription(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Class '${oldClass?.name}' supertypes changed. " +
                "Was: '${oldClass?.supertypes?.joinToString()}'; " +
                "now: ${newClass?.supertypes?.joinToString()}"
    }

    override fun matches(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Boolean {
        return when {
            oldClass == null && newClass == null -> false
            oldClass == null && newClass != null -> false
            oldClass != null && newClass != null -> {
                oldClass.supertypes != newClass.supertypes
            }
            else -> false
        }
    }
}
