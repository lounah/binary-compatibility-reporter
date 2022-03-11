package com.lounah.ktbinaryreporter.internal.compatators.util

import org.objectweb.asm.tree.AnnotationNode
import kotlinx.validation.api.FieldBinarySignature

internal inline val FieldBinarySignature.isNullable: Boolean
    get() = annotations.any { it.isNullable }

internal inline val AnnotationNode.isNullable: Boolean
    get() = desc.contains("Nullable", ignoreCase = true)
