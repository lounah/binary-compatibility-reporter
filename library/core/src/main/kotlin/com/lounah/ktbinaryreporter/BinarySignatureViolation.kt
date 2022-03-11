package com.lounah.ktbinaryreporter

import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature
import kotlinx.validation.api.MethodBinarySignature

public interface BinaryCompatibilityViolation {

    public val description: String

    public class ClassSignature(
        private val oldClass: ClassBinarySignature?,
        private val newClass: ClassBinarySignature?,
        override val description: String
    ) : BinaryCompatibilityViolation

    public class MethodSignature(
        private val clazz: ClassBinarySignature?,
        private val oldMethod: MethodBinarySignature?,
        private val newMethod: MethodBinarySignature?,
        override val description: String
    ) : BinaryCompatibilityViolation

    public class FieldSignature(
        private val clazz: ClassBinarySignature?,
        private val oldField: FieldBinarySignature?,
        private val newField: FieldBinarySignature?,
        override val description: String
    ) : BinaryCompatibilityViolation

    public object Empty : BinaryCompatibilityViolation {

        override val description: String = ""
    }
}
