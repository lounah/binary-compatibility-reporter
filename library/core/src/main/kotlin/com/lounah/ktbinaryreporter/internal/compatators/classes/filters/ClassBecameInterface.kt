package com.lounah.ktbinaryreporter.internal.compatators.classes.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isInterface
import kotlinx.validation.api.ClassBinarySignature

internal class ClassBecameInterface : ViolationRule.Class {

    override fun describe(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Class `${oldClass?.name}` became interface."
    }

    override fun matches(
        oldSignature: ClassBinarySignature?,
        newSignature: ClassBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                !oldSignature.access.isInterface && newSignature.access.isInterface
            }
            else -> false
        }
    }
}
