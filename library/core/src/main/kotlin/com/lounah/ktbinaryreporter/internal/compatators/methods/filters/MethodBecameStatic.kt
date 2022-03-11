package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodBecameStatic : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return "Method `${clazz?.name}#${newMethod?.name}` became static."
    }

    override fun matches(clazz: ClassBinarySignature?, oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.becameStatic(newSignature)
            }
            else -> false
        }
    }

    private fun MethodBinarySignature.becameStatic(compareTo: MethodBinarySignature): Boolean {
        return access != compareTo.access && !access.isStatic && compareTo.access.isStatic
    }
}
