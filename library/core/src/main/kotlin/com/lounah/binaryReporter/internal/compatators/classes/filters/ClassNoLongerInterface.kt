package com.lounah.binaryReporter.internal.compatators.classes.filters

import com.lounah.binaryReporter.ViolationRule
import com.lounah.binaryReporter.internal.compatators.util.isInterface
import kotlinx.validation.api.ClassBinarySignature

internal class ClassNoLongerInterface : ViolationRule {

    override fun getDescription(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Class '${oldClass?.name}' was interface, now it is not."
    }

    override fun matches(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Boolean {
        return when {
            oldClass == null && newClass == null -> false
            oldClass == null && newClass != null -> false
            oldClass != null && newClass != null -> {
                oldClass.access.isInterface && !newClass.access.isInterface
            }
            else -> false
        }
    }
}
