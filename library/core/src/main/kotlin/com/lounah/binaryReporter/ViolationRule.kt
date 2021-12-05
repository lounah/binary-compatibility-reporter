package com.lounah.binaryReporter

import kotlinx.validation.api.ClassBinarySignature

public interface ViolationRule {

    public fun getDescription(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String

    public fun matches(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Boolean
}
