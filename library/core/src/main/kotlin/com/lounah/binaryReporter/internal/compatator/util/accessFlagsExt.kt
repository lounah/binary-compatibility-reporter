package com.lounah.binaryReporter.internal.compatator.util

import jdk.internal.org.objectweb.asm.Opcodes
import kotlinx.validation.api.AccessFlags

internal val AccessFlags.isAbstract: Boolean
    get() = isAbstract(access)

internal fun isAbstract(access: Int) = access and Opcodes.ACC_ABSTRACT != 0