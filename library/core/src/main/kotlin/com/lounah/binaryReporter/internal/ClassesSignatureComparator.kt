package com.lounah.binaryReporter.internal

import com.lounah.binaryReporter.BinaryCompatibilityViolation
import com.lounah.binaryReporter.BinarySignatureComparator
import kotlinx.validation.api.ClassBinarySignature

internal class ClassesSignatureComparator : BinarySignatureComparator {

    override fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation> {
        TODO("Not yet implemented")
    }
}
