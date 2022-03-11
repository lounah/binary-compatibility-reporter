package com.lounah.ktbinaryreporter.internal.compatators.classes.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.internal.compatators.util.isAnnotation
import kotlinx.validation.api.ClassBinarySignature

internal class ClassNoLongerAnnotation : ViolationRule.Class {

    override fun describe(
        oldClass: ClassBinarySignature?,
        newClass: ClassBinarySignature?
    ): String {
        return "Class `${oldClass?.name}` was an annotation, now it is not."
    }

    override fun matches(
        oldSignature: ClassBinarySignature?,
        newSignature: ClassBinarySignature?
    ): Boolean {
        return when {
            oldSignature != null && newSignature != null -> {
                oldSignature.access.isAnnotation && !newSignature.access.isAnnotation
            }
            else -> false
        }
    }
}
