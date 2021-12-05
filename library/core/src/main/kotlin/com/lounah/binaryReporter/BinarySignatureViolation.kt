package com.lounah.binaryReporter

import kotlinx.validation.api.ClassBinarySignature

public interface BinaryCompatibilityViolation {

    public val oldSignature: ClassBinarySignature?
    public val newSignature: ClassBinarySignature?
    public val description: String

    public class ClassSignature(
        override val oldSignature: ClassBinarySignature?,
        override val newSignature: ClassBinarySignature?,
        override val description: String
    ) : BinaryCompatibilityViolation

    public class MethodSignature(
        override val oldSignature: ClassBinarySignature?,
        override val newSignature: ClassBinarySignature?,
        override val description: String
    ) : BinaryCompatibilityViolation

    public class FieldSignature(
        override val oldSignature: ClassBinarySignature?,
        override val newSignature: ClassBinarySignature?,
        override val description: String
    ) : BinaryCompatibilityViolation

    public object Empty : BinaryCompatibilityViolation {
        override val oldSignature: ClassBinarySignature? = null
        override val newSignature: ClassBinarySignature? = null
        override val description: String = ""
    }
}
