package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isAbstract
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodBecameFinal : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return "Method `${clazz?.name}#${newMethod?.name}` became final."
    }

    override fun matches(clazz: ClassBinarySignature?, oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.becameFinal(newSignature)
            }
            else -> false
        }
    }

    private fun MethodBinarySignature.becameFinal(compareTo: MethodBinarySignature): Boolean {
        return access != compareTo.access && !access.isFinal && compareTo.access.isFinal
    }
}
