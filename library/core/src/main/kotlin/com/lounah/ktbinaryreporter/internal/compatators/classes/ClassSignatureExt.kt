package com.lounah.ktbinaryreporter.internal.compatators.classes

import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.FieldBinarySignature

internal val ClassBinarySignature.shortenName: String
    get() = name.substringAfterLast("/")