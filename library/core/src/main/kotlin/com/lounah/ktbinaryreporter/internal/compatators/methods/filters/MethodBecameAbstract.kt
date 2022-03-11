package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isAbstract
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodBecameAbstract : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return "Method `${clazz?.name}#${newMethod?.name} became abstract.`"
    }

    override fun matches(oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.access.isAbstract.not() && newSignature.access.isAbstract
            }
            else -> false
        }
    }
}
