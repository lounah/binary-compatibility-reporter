package com.lounah.ktbinaryreporter.internal.compatators.classes.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isAbstract
import kotlinx.validation.api.ClassBinarySignature

internal class ClassBecameAbstract : ViolationRule.Class {

    override fun describe(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Non-abstract class `${oldClass?.name}` became abstract."
    }

    override fun matches(
        oldSignature: ClassBinarySignature?,
        newSignature: ClassBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                !oldSignature.access.isAbstract && newSignature.access.isAbstract
            }
            else -> false
        }
    }
}
