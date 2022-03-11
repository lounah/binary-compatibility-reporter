package com.lounah.ktbinaryreporter.internal.compatators.classes

import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation
import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation.ClassSignature
import com.lounah.ktbinaryreporter.BinarySignatureComparator
import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.*
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassBecameAbstract
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassBecameFinal
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassBecameInterface
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassNameChanged
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassSupertypesChanged
import com.lounah.ktbinaryreporter.internal.compatators.classes.filters.ClassVisibilityLessen
import kotlinx.validation.api.ClassBinarySignature

internal class ClassesSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule.Class> = setOf(
        ClassNameChanged(),
        ClassVisibilityLessen(),
        ClassSupertypesChanged(),
        ClassBecameFinal(),
        ClassBecameAbstract(),
        ClassBecameInterface(),
        ClassNoLongerInterface(),
        ClassBecameAnnotation(),
        ClassNoLongerAnnotation()
    )

    override fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation> {
        return rules
            .asSequence()
            .filter { rule -> rule.matches(oldClass, newClass) }
            .map { rule -> ClassSignature(oldClass, newClass, rule.describe(oldClass, newClass)) }
            .toSet()
    }
}
