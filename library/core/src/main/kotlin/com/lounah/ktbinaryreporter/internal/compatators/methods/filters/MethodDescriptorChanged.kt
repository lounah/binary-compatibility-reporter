package com.lounah.ktbinaryreporter.internal.compatators.methods.filters

import com.lounah.ktbinaryreporter.ViolationRule
import com.lounah.ktbinaryreporter.descriptor.DescriptorDiffer
import com.lounah.ktbinaryreporter.descriptor.DescriptorParser
import com.lounah.ktbinaryreporter.internal.compatators.classes.shortenName
import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.MethodBinarySignature

internal class MethodDescriptorChanged : ViolationRule.Method {

    override fun describe(
        clazz: ClassBinarySignature?,
        oldMethod: MethodBinarySignature,
        newMethod: MethodBinarySignature?
    ): String {
        val oldDesc = oldMethod.shortenDescriptor()
        val parameterWasMoved = oldDesc == newMethod?.shortenDescriptor() && oldDesc.isNotEmpty()
        val methodDescription = oldMethod.extractMethodDescription(clazz)
        val diffDescriptor = oldMethod.diffDescriptor(newMethod)

        return when {
            parameterWasMoved -> {
                """
                    $methodDescription parameter was moved.
                    Was: `${oldMethod.shortenDescriptor(withPath = true)}`;
                    Now: `${newMethod?.shortenDescriptor(withPath = true)}`.
                """.trimIndent()
            }
            diffDescriptor.isNotEmpty() -> "$methodDescription descriptor changed. $diffDescriptor"
            else -> ""
        }
    }

    override fun matches(clazz: ClassBinarySignature?, oldSignature: MethodBinarySignature?, newSignature: MethodBinarySignature?): Boolean {
        return when {
            !oldSignature.isDataClassSpecific() && oldSignature.isDescriptorChanged(newSignature) -> true
            else -> false
        }
    }

    private fun MethodBinarySignature?.isDescriptorChanged(compareTo: MethodBinarySignature?): Boolean {
        return this != null && compareTo != null && desc != compareTo.desc
    }

    private fun MethodBinarySignature?.isCopy(): Boolean {
        val name = this?.name.orEmpty()
        return name == "copy" || name == "copy\$default"
    }

    private fun MethodBinarySignature?.isComponent(): Boolean {
        val componentRegex = "^(component[0-9]+)".toRegex()
        return this?.name.orEmpty().matches(componentRegex)
    }

    private fun MethodBinarySignature?.isDataClassSpecific(): Boolean {
        return isCopy() || isComponent()
    }

    private fun MethodBinarySignature.shortenDescriptor(withPath: Boolean = false): String {
        return "(${
            DescriptorParser().parse(desc)
                .joinToString(transform = { if (!withPath) it.substringAfterLast("/") else it })
        })".removePrefix("()")
    }

    private fun MethodBinarySignature?.extractMethodDescription(hostClass: ClassBinarySignature?): String {
        val isConstructor = isConstructor()
        val method = if (isConstructor) "Constructor" else "Method"
        return "$method ${hostClass?.shortenName}" + if (!isConstructor) "#${this?.name}" else ""
    }

    private fun MethodBinarySignature?.isConstructor(): Boolean = this?.name.orEmpty() == "<init>"

    private fun MethodBinarySignature.diffDescriptor(with: MethodBinarySignature?): String {
        return DescriptorDiffer().calculateDiff(desc, with?.desc.orEmpty())
    }
}
