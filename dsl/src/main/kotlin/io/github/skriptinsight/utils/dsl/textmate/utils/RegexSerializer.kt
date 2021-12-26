package io.github.skriptinsight.utils.dsl.textmate.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

object RegexSerializer : StdSerializer<Regex>(Regex::class.java) {
    override fun serialize(value: Regex?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeString(value?.pattern ?: "")
    }
}