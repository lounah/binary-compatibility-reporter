package com.lounah.ktbinaryreporter.internal.compatators.classes

import kotlinx.validation.api.ClassBinarySignature

internal val ClassBinarySignature.shortenName: String
    get() = name.substringAfterLast("/")