package com.lounah.ktbinaryreporter.internal.compatators.classes.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature

internal class ClassSupertypesChanged : ViolationRule.Class {

    override fun describe(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return """
            Class `${oldClass?.name}` supertypes changed.
            Was: `${oldClass?.supertypes?.joinToString()}`;
            Now: `${newClass?.supertypes?.joinToString()}`
        """.trimIndent()
    }

    override fun matches(
        oldSignature: ClassBinarySignature?,
        newSignature: ClassBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.supertypes != newSignature.supertypes
            }
            else -> false
        }
    }
}
