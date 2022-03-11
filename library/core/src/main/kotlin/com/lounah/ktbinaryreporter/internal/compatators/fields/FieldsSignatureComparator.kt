package com.lounah.ktbinaryreporter.internal.compatators.fields

import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation
import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation.FieldSignature
import com.lounah.ktbinaryreporter.BinarySignatureComparator
import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassBecameFinal
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassNameChanged
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassVisibilityLessen
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassSupertypesChanged
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

public class FieldsSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule.Class> = setOf(
        ClassNameChanged(),
        ClassVisibilityLessen(),
        ClassSupertypesChanged(),
        ClassBecameFinal()
    )

    override fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation> {
        val oldMethods = oldClass?.memberSignatures.orEmpty()
            .filterIsInstance<MethodBinarySignature>()
        val newMethods = newClass?.memberSignatures.orEmpty()
            .filterIsInstance<MethodBinarySignature>()

        return rules
            .asSequence()
            .filter { rule -> rule.matches(oldClass, newClass) }
            .map { rule -> FieldSignature(oldClass, newClass, rule.describe(oldClass, newClass)) }
            .toSet()
    }

    private fun ClassBinarySignature?.extractMethods(): List<MethodBinarySignature> {
        return this?.memberSignatures?.filterIsInstance<MethodBinarySignature>().orEmpty()
    }
}
