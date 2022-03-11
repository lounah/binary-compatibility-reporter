package com.lounah.ktbinaryreporter

import com.lounah.ktbinaryreporter.internal.KotlinBinaryCompatibilityChecker
import com.lounah.ktbinaryreporter.internal.compatators.classes.ClassesSignatureComparator
import com.lounah.ktbinaryreporter.internal.compatators.fields.FieldsSignatureComparator
import com.lounah.ktbinaryreporter.internal.compatators.methods.MethodsSignatureComparator
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
