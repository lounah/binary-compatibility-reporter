package com.lounah.ktbinaryreporter.internal.compatators.fields.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature

internal class FieldWasRenamedOrRemoved : ViolationRule.Field {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldField: FieldBinarySignature,
        newField: FieldBinarySignature?
    ): String {
        return "Field `${clazz?.name}$${oldField.name}` was renamed or removed."
    }

    override fun matches(
        clazz: ClassBinarySignature?,
        oldSignature: FieldBinarySignature?,
        newSignature: FieldBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.name != newSignature.name
            }
            else -> false
        }
    }
}