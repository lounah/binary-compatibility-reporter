package com.lounah.binaryReporter

import com.lounah.binaryReporter.internal.KotlinBinaryCompatibilityChecker
import com.lounah.binaryReporter.internal.compatator.ClassesSignatureComparator
import com.lounah.binaryReporter.internal.compatator.FieldsSignatureComparator
import com.lounah.binaryReporter.internal.compatator.MethodsSignatureComparator
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
