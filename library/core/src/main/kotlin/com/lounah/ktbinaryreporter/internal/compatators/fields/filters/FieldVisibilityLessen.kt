package com.lounah.ktbinaryreporter.internal.compatators.fields.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature

internal class FieldVisibilityLessen : ViolationRule.Field {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldField: FieldBinarySignature,
        newField: FieldBinarySignature?
    ): String {
        return """
            Field `${clazz?.name}$${oldField.name}` visibility lessen.
            Was: `${oldField.access.getModifierString()}`;
            Now: `${newField?.access?.getModifierString()}`.
        """.trimIndent()
    }

    override fun matches(
        clazz: ClassBinarySignature?,
        oldSignature: FieldBinarySignature?,
        newSignature: FieldBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.access.isPublic && !newSignature.access.isPublic
            }
            else -> false
        }
    }
}
