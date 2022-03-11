package com.lounah.ktbinaryreporter.internal.compatators.fields.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isDeprecated
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature

internal class FieldWasDeprecated : ViolationRule.Field {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldField: FieldBinarySignature,
        newField: FieldBinarySignature?
    ): String {
        return "Field `${clazz?.name}$${oldField.name}` became deprecated."
    }

    override fun matches(
        clazz: ClassBinarySignature?,
        oldSignature: FieldBinarySignature?,
        newSignature: FieldBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.becameDeprecated(newSignature)
            }
            else -> false
        }
    }

    private fun FieldBinarySignature.becameDeprecated(compareTo: FieldBinarySignature): Boolean {
        return access != compareTo.access && !access.isDeprecated && compareTo.access.isDeprecated
    }
}