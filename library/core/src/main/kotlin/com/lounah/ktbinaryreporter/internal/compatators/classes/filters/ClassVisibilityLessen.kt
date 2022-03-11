package com.lounah.ktbinaryreporter.internal.compatators.classes.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassVisibilityLessen : ViolationRule.Class {

    override fun describe(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return """
            Class `${oldClass?.name}` visibility lessen.
            Was: `${oldClass?.access?.getModifierString()}`;
            Now: `${newClass?.access?.getModifierString()}`.
        """.trimIndent()
    }

    override fun matches(
        oldSignature: ClassBinarySignature?,
        newSignature: ClassBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.access.isPublic && !newSignature.access.isPublic
            }
            else -> false
        }
    }
}
