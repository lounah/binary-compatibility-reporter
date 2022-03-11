package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodWasRenamedOrRemoved : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        return "Method `${clazz?.name}#${oldMethod.name}` was renamed or deleted."
    }

    override fun matches(oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.name != newSignature.name
            }
            else -> false
        }
    }
}
