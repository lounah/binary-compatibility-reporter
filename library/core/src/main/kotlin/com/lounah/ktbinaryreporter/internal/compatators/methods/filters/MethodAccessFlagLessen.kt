package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodAccessFlagLessen : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return """
            Access flag of `${clazz?.name}#${newMethod?.name} lessen.`
            Was: 
        """.trimIndent()
    }

    override fun matches(oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.access != newSignature.access
            }
            else -> false
        }
    }
}