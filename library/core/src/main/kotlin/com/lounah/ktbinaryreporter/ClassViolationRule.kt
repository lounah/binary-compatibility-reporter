package com.lounah.ktbinaryreporter

import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

public interface ViolationRule<T> {

    public fun matches(oldSignature: T?, newSignature: T?): Boolean

    public interface Class : ViolationRule<ClassBinarySignature> {

        public fun describe(oldClass: ClassBinarySignature?, newClass: ClassBinarySignature?): String
    }

    public interface Method : ViolationRule<MethodBinarySignature> {

        public fun describe(
            clazz: ClassBinarySignature?,
            oldMethod: MethodBinarySignature,
            newMethod: MethodBinarySignature?
        ): String
    }
}
