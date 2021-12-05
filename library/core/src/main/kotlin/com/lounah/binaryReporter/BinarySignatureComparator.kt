package com.lounah.binaryReporter

import kotlinx.validation.api.ClassBinarySignature

public interface BinarySignatureComparator {

    public fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation>
}
