package com.lounah.ktbinaryreporter.descriptor

public interface DescriptorParser {

    public fun parse(desc: String): List<String>

    public companion object {

        public operator fun invoke(): DescriptorParser = Impl()
    }

    private class Impl : DescriptorParser {

        private companion object {
            const val DESCRIPTOR_PATTERN = "\\[*L[^;]+;|\\[[ZBCSIFDJ]|[ZBCSIFDJ]"
            const val BYTECODE_INT = "I"
            const val BYTECODE_DOUBLE = "D"
            const val BYTECODE_LONG = "J"
            const val BYTECODE_FLOAT = "F"
            const val BYTECODE_CHAR = "C"
            const val BYTECODE_BOOLEAN = "Z"
            const val BYTECODE_SHORT = "S"
            const val BYTECODE_BYTE = "B"
        }

        override fun parse(desc: String): List<String> {
            val beginIndex = desc.indexOf("(")
            val endIndex = desc.indexOf(")")
            val descRegex = DESCRIPTOR_PATTERN.toRegex()
            val body = if (beginIndex == -1 && endIndex == -1) desc else desc.substring(beginIndex + 1, endIndex)
            val parameters = descRegex.findAll(body).map { it.value }.toList()
            return parameters.map(::parseDescParameter)
        }

        private fun parseDescParameter(param: String): String {
            return when {
                param == BYTECODE_INT -> "Int"
                param == BYTECODE_DOUBLE -> "Double"
                param == BYTECODE_LONG -> "Long"
                param == BYTECODE_FLOAT -> "Float"
                param == BYTECODE_CHAR -> "Char"
                param == BYTECODE_BOOLEAN -> "Boolean"
                param == BYTECODE_SHORT -> "Short"
                param == BYTECODE_BYTE -> "Byte"
                param.startsWith("L") -> param.substringAfter("L")
                else -> ""
            }.removeSuffix(";")
        }
    }
}
