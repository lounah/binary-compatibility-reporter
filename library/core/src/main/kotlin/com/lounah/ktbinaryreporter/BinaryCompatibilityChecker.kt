package com.lounah.ktbinaryreporter

import com.lounah.ktbinaryreporter.internal.KotlinBinaryCompatibilityChecker
import com.lounah.ktbinaryreporter.internal.compatators.classes.ClassesSignatureComparator
import com.lounah.ktbinaryreporter.internal.compatators.fields.FieldsSignatureComparator
import com.lounah.ktbinaryreporter.internal.compatators.methods.MethodsSignatureComparator
import kotlinx.validation.api.ClassBinarySignature
import java.util.jar.JarFile

public interface BinaryCompatibilityChecker {

    public fun check(oldClasses: List<ClassBinarySignature>, newClasses: List<ClassBinarySignature>): Verdict

    public companion object {

        public operator fun invoke(): BinaryCompatibilityChecker {
            return KotlinBinaryCompatibilityChecker(
                ClassesSignatureComparator(),
                MethodsSignatureComparator(),
                FieldsSignatureComparator()
            )
        }
    }
}
