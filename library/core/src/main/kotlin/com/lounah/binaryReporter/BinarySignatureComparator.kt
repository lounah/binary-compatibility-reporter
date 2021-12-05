package com.lounah.binaryReporter

import kotlinx.validation.api.ClassBinarySignature

public interface BinarySignatureComparator {

    public val rules: Set<ViolationRule>

    public fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation> {
        return rules
            .asSequence()
            .map { rule -> rule.check(oldClass, newClass) }
            .filter { it !is BinaryCompatibilityViolation.Empty }
            .toSet()
    }

    public interface ViolationRule {

        public fun check(
            oldClass: ClassBinarySignature?,
            newClass: ClassBinarySignature?
        ): BinaryCompatibilityViolation
    }
}
