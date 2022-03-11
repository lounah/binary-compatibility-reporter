package com.lounah.ktbinaryreporter.internal.compatators.fields

import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation
import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation.FieldSignature
import com.lounah.ktbinaryreporter.BinarySignatureComparator
import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassBecameFinal
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassNameChanged
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassVisibilityLessen
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassSupertypesChanged
import com.lounah.ktbinaryreporter.internal.compatators.fields.filters.FieldWasDeprecated
import com.lounah.ktbinaryreporter.internal.compatators.fields.filters.FieldWasRenamedOrRemoved
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature

public class FieldsSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule.Field> = setOf(
        FieldWasDeprecated(),
        FieldWasRenamedOrRemoved()
    )

    override fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation> {
        return oldClass.extractPublicFields()
            .associateByMethodName(newClass.extractPublicFields())
            .flatMap { (oldField, newField) ->
                rules
                    .asSequence()
                    .filter { rule -> rule.matches(oldClass, oldField, newField) }
                    .map { rule ->
                        FieldSignature(
                            oldClass,
                            oldField,
                            newField,
                            rule.describe(oldClass, oldField, newField)
                        )
                    }
            }
            .toSet()
    }

    private fun ClassBinarySignature?.extractPublicFields(): List<FieldBinarySignature> {
        return this?.memberSignatures?.filterIsInstance<FieldBinarySignature>()?.filter {
            it.access.isPublic && !(it.access.isProtected && it.access.isFinal)
        }.orEmpty()
    }

    private fun List<FieldBinarySignature>.associateByMethodName(
        otherFields: List<FieldBinarySignature>
    ): Map<FieldBinarySignature, FieldBinarySignature?> {
        return associateWith { fieldSignature ->
            otherFields.firstOrNull { fieldSignature.name == it.name }
        }
    }
}
