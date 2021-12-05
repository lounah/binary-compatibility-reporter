package com.lounah.binaryReporter

import com.lounah.binaryReporter.BinaryCompatibilityViolation.*

public data class Verdict(
    public val violations: List<BinaryCompatibilityViolation>
) {

    val classesViolations: List<BinaryCompatibilityViolation>
        get() = violations.filterIsInstance<ClassSignature>()

    val methodsViolations: List<BinaryCompatibilityViolation>
        get() = violations.filterIsInstance<MethodSignature>()

    val fieldsViolations: List<BinaryCompatibilityViolation>
        get() = violations.filterIsInstance<FieldSignature>()

    override fun toString(): String {
        return when {
            violations.isEmpty() -> "No binary incompatible changes found."
            else -> {
                "Found ${violations.size} binary incompatible changes:\n" +
                        describeViolations()
            }
        }
    }

    private fun describeViolations(): String {
        return violations
            .joinToString("\n", transform = BinaryCompatibilityViolation::description)
    }
}
