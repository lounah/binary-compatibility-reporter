package com.lounah.binaryReporter.internal.compatators.classes.filters

import com.lounah.binaryReporter.ViolationRule
import com.lounah.binaryReporter.internal.compatators.util.isAbstract
import kotlinx.validation.api.ClassBinarySignature

internal class ClassBecameAbstract : ViolationRule {

    override fun getDescription(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Non-abstract class '${oldClass?.name}' became abstract."
    }

    override fun matches(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Boolean {
        return when {
            oldClass == null && newClass == null -> false
            oldClass == null && newClass != null -> false
            oldClass != null && newClass != null -> {
                !oldClass.access.isAbstract && newClass.access.isAbstract
            }
            else -> false
        }
    }
}
