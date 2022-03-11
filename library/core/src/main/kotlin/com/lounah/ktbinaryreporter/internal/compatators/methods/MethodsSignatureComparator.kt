package com.lounah.ktbinaryreporter.internal.compatators.methods

import com.lounah.ktbinaryreporter.*
import com.lounah.ktbinaryreporter.BinaryCompatibilityViolation.MethodSignature
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.*
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.AbsMethodAdded
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.MethodAccessFlagLessen
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.MethodBecameAbstract
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.MethodBecameFinal
import com.lounah.ktbinaryreporter.internal.compatators.methods.filters.MethodWasRenamedOrRemoved
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

public class MethodsSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule.Method> = setOf(
        AbsMethodAdded(),
        MethodBecameAbstract(),
        MethodWasRenamedOrRemoved(),
        MethodAccessFlagLessen(),
        MethodBecameFinal(),
        MethodBecameStatic(),
        MethodDescriptorChanged(),
        MethodBecameDeprecated()
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
                    .filter { rule -> rule.matches(oldClass, oldMethod, newMethod) }
                    .map { rule ->
                        MethodSignature(oldClass, oldMethod, newMethod, rule.describe(oldClass, oldMethod, newMethod))
                    }
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
