package com.lounah.binaryReporter.internal.compatator

import com.lounah.binaryReporter.BinarySignatureComparator
import com.lounah.binaryReporter.BinarySignatureComparator.ViolationRule
import com.lounah.binaryReporter.internal.compatator.filters.`class`.ClassBecameFinal
import com.lounah.binaryReporter.internal.compatator.filters.`class`.ClassNameChanged
import com.lounah.binaryReporter.internal.compatator.filters.`class`.ClassVisibilityLessen
import com.lounah.binaryReporter.internal.compatator.filters.`class`.ClassSupertypesChanged

public class FieldsSignatureComparator : BinarySignatureComparator {

    override val rules: Set<ViolationRule> = setOf(
        ClassNameChanged(),
        ClassVisibilityLessen(),
        ClassSupertypesChanged(),
        ClassBecameFinal()
    )
}
