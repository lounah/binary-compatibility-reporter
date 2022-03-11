package com.lounah.ktbinaryreporter

import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature
import kotlinx.validation.api.MethodBinarySignature

public interface ViolationRule {

    public interface Class : ViolationRule {

        public fun matches(oldSignature: ClassBinarySignature?, newSignature: ClassBinarySignature?): Boolean

        public fun describe(oldClass: ClassBinarySignature?, newClass: ClassBinarySignature?): String
    }

    public interface Method : ViolationRule {

        public fun matches(
            clazz: ClassBinarySignature?,
            oldSignature: MethodBinarySignature?,
            newSignature: MethodBinarySignature?
        ): Boolean

        public fun describe(
            clazz: ClassBinarySignature?,
            oldMethod: MethodBinarySignature,
            newMethod: MethodBinarySignature?
        ): String
    }

    public interface Field : ViolationRule {

        public fun matches(
            clazz: ClassBinarySignature?,
            oldSignature: FieldBinarySignature?,
            newSignature: FieldBinarySignature?
        ): Boolean

        public fun describe(
            clazz: ClassBinarySignature?,
            oldField: FieldBinarySignature,
            newField: FieldBinarySignature?
        ): String
    }
}
