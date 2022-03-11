package com.lounah.ktbinaryreporter.internal.compatators.methods

import com.lounah.ktbinaryreporter.*
import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation.MethodSignature
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.AbsMethodAdded
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.MethodBecameAbstract
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.MethodWasRenamedOrRemoved
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

public class MethodsSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule.Method> = setOf(
        AbsMethodAdded(),
        MethodBecameAbstract(),
        MethodWasRenamedOrRemoved()
    )

    override fun compare(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): Set<BinaryCompatibilityViolation> {
        return oldClass.extractMethods()
            .associateByMethodName(newClass.extractMethods())
            .flatMap { (oldMethod, newMethod) ->
                rules
                    .asSequence()
                    .filter { rule -> rule.matches(oldMethod, newMethod) }
                    .map { rule -> MethodSignature(oldClass, newClass, rule.describe(oldClass, oldMethod, newMethod)) }
                    .toSet()
            }
            .toSet()
    }

    private fun ClassBinarySignature?.extractMethods(): List<MethodBinarySignature> {
        return this?.memberSignatures.orEmpty()
            .filterIsInstance<MethodBinarySignature>()
    }

    private fun List<MethodBinarySignature>.associateByMethodName(
        otherMethods: List<MethodBinarySignature>
    ): Map<MethodBinarySignature, MethodBinarySignature?> {
        return associateWith { methodSignature ->
            otherMethods.firstOrNull { methodSignature.name == it.name }
        }
    }
}
