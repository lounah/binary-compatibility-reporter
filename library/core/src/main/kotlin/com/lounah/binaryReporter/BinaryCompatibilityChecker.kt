package com.lounah.binaryReporter

import com.lounah.binaryReporter.internal.KotlinBinaryCompatibilityChecker
import com.lounah.binaryReporter.internal.compatators.classes.ClassesSignatureComparator
import com.lounah.binaryReporter.internal.compatators.fields.FieldsSignatureComparator
import com.lounah.binaryReporter.internal.compatators.methods.MethodsSignatureComparator
import java.util.jar.JarFile

public interface BinaryCompatibilityChecker {

    public fun check(oldClasses: JarFile, newClasses: JarFile): Verdict

    public companion object {

        public operator fun invoke(
            ignoredPackages: Set<String>,
            ignoredClasses: Set<String>
        ): BinaryCompatibilityChecker {
            return KotlinBinaryCompatibilityChecker(
                ignoredPackages,
                ignoredClasses,
                ClassesSignatureComparator(),
                MethodsSignatureComparator(),
                FieldsSignatureComparator()
            )
        }
    }
}
