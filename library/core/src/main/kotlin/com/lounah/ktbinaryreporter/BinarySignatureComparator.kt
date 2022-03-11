package com.lounah.ktbinaryreporter

import kotlinx.validation.api.ClassBinarySignature

public interface BinarySignatureComparator {

    public val rules: Set<ViolationRule<*>>

    public fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation>
}
