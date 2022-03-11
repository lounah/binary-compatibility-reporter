package com.lounah.ktbinaryreporter.internal.compatators.methods

import com.lounah.ktbinaryreporter.descriptor.DescriptorParser
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal fun MethodBinarySignature?.extractMethodDescription(hostClass: ClassBinarySignature?): String {
    val isConstructor = isConstructor()
    val method = if (isConstructor) "Constructor" else "Method"
    return "$method ${hostClass?.shortenName}" + if (!isConstructor) "#${this?.name}" else ""
}

internal fun MethodBinarySignature.shortenDescriptor(withPath: Boolean = false): String {
    return "(${
        DescriptorParser().parse(desc)
            .joinToString(transform = { if (!withPath) it.substringAfterLast("/") else it })
    })".removePrefix("()")
}

internal fun MethodBinarySignature?.isConstructor(): Boolean = this?.name.orEmpty() == "<init>"

internal fun MethodBinarySignature?.isCopy(): Boolean {
    val name = this?.name.orEmpty()
    return name == "copy" || name == "copy\$default"
}

internal fun MethodBinarySignature?.isComponent(): Boolean {
    val componentRegex = "^(component[0-9]+)".toRegex()
    return this?.name.orEmpty().matches(componentRegex)
}

internal fun MethodBinarySignature?.isDataClassSpecific(): Boolean {
    return isCopy() || isComponent()
}