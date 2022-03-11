package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isDeprecated
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodBecameDeprecated : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return "Method `${clazz?.name}#${newMethod?.name}` became deprecated."
    }

    override fun matches(clazz: ClassBinarySignature?, oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.becameDeprecated(newSignature)
            }
            else -> false
        }
    }

    private fun MethodBinarySignature.becameDeprecated(compareTo: MethodBinarySignature): Boolean {
        return access != compareTo.access && !access.isDeprecated && compareTo.access.isDeprecated
    }
}
