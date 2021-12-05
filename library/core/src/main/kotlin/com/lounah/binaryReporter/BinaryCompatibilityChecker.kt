package com.lounah.binaryReporter

import com.lounah.binaryReporter.BinaryCompatibilityViolation.*
import java.util.jar.JarFile

public interface BinaryCompatibilityChecker {

    public fun check(oldClasses: JarFile, newClasses: JarFile): Verdict

    public data class Verdict(
        public val violations: List<BinaryCompatibilityViolation>
    ) {

        val classesViolations: List<BinaryCompatibilityViolation>
            get() = violations.filterIsInstance<ClassSignature>()

        val methodsViolations: List<BinaryCompatibilityViolation>
            get() = violations.filterIsInstance<MethodSignature>()

        val fieldsViolations: List<BinaryCompatibilityViolation>
            get() = violations.filterIsInstance<FieldSignature>()
    }
}
