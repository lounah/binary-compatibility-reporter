package com.lounah.binaryReporter.internal.compatators.classes

import com.lounah.binaryReporter.BinaryCompatibilityViolation
import com.lounah.binaryReporter.BinaryCompatibilityViolation.ClassSignature
import com.lounah.binaryReporter.BinarySignatureComparator
import com.lounah.binaryReporter.ViolationRule
import com.lounah.binaryReporter.internal.compatators.classes.filters.*
import com.lounah.binaryReporter.internal.compatators.classes.filters.ClassBecameAbstract
import com.lounah.binaryReporter.internal.compatators.classes.filters.ClassBecameFinal
import com.lounah.binaryReporter.internal.compatators.classes.filters.ClassBecameInterface
import com.lounah.binaryReporter.internal.compatators.classes.filters.ClassNameChanged
import com.lounah.binaryReporter.internal.compatators.classes.filters.ClassSupertypesChanged
import com.lounah.binaryReporter.internal.compatators.classes.filters.ClassVisibilityLessen
import kotlinx.validation.api.ClassBinarySignature

internal class ClassesSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule> = setOf(
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
            .map { rule -> ClassSignature(oldClass, newClass, rule.getDescription(oldClass, newClass)) }
            .toSet()
    }
}
