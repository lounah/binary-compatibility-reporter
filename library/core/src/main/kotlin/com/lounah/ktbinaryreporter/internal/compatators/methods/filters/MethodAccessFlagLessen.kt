package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.AccessFlags
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodAccessFlagLessen : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return """
            Access flag of `${clazz?.name}#${newMethod?.name}` lessen.
            Was: `${oldMethod.access.getModifierString()}`;
            Now: `${newMethod?.access?.getModifierString()}`.
        """.trimIndent()
    }

    override fun matches(clazz: ClassBinarySignature?, oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                val classAccess = clazz?.access ?: AccessFlags(0)
                oldSignature.isAccessFlagLessen(newSignature, classAccess)
            }
            else -> false
        }
    }

    private fun MethodBinarySignature.isAccessFlagLessen(
        compareTo: MethodBinarySignature,
        classAccess: AccessFlags
    ): Boolean {
        return access != compareTo.access && isEffectivelyPublic(classAccess, null) &&
                !compareTo.isEffectivelyPublic(classAccess, null)
    }
}