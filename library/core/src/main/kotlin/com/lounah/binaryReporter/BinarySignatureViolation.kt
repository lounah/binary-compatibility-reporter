package com.lounah.binaryReporter

import kotlinx.validation.api.ClassBinarySignature

public interface BinaryCompatibilityViolation {

    public val source: ClassBinarySignature

    public val description: String

    public class ClassSignature(
        override val source: ClassBinarySignature,
        override val description: String
    ) : BinaryCompatibilityViolation

    public class MethodSignature(
        override val source: ClassBinarySignature,
        override val description: String
    ) : BinaryCompatibilityViolation

    public class FieldSignature(
        override val source: ClassBinarySignature,
        override val description: String
    ) : BinaryCompatibilityViolation
}
