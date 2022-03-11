package com.lounah.ktbinaryreporter.descriptor

public interface DescriptorDiffer {

    public fun calculateDiff(oldDesc: String, newDesc: String): String

    public companion object {

        public operator fun invoke(): DescriptorDiffer {
            return Impl(DescriptorParser())
        }
    }

    private class Impl(private val parser: DescriptorParser) : DescriptorDiffer {

        override fun calculateDiff(oldDesc: String, newDesc: String): String {
            val oldParams = parser.parse(oldDesc).map { it.substringAfterLast("/") }
            val newParams = parser.parse(newDesc).map { it.substringAfterLast("/") }
            val diff = DiffUtil.difference(oldParams, newParams)
            val addedParams = diff.added
                .joinToString()
                .run { if (isNotEmpty()) "+[$this]" else "" }
            val removedParams = diff.removed
                .joinToString()
                .run { if (isNotEmpty()) "-[$this]" else "" }

            return "$addedParams $removedParams"
        }
    }
}
