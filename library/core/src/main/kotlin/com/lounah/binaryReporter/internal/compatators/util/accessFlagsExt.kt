package com.lounah.binaryReporter.internal.compatators.util

import jdk.internal.org.objectweb.asm.Opcodes
import kotlinx.validation.api.AccessFlags

internal inline val AccessFlags.isAbstract: Boolean
    get() = access and Opcodes.ACC_ABSTRACT != 0

internal inline val AccessFlags.isInterface: Boolean
    get() = access and Opcodes.ACC_INTERFACE != 0

internal inline val AccessFlags.isAnnotation: Boolean
    get() = access and Opcodes.ACC_ANNOTATION != 0
