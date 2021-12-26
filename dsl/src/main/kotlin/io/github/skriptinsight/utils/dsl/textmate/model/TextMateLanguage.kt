package io.github.skriptinsight.utils.dsl.textmate.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer
import com.fasterxml.jackson.module.kotlin.jsonMapper
import io.github.skriptinsight.utils.dsl.textmate.annotations.LanguageDslMarker
import io.github.skriptinsight.utils.dsl.textmate.utils.RegexSerializer
import java.io.File
import java.util.*

@LanguageDslMarker
class TextMateLanguage {
    var scopeName: String? = null
    var uuid: UUID? = null
    val repository = mutableMapOf<String, TextMateRule>()
    val patterns = mutableListOf<TextMateRule>()
    val captures = mutableMapOf<String, TextMateRule>()

    operator fun TextMateRule.unaryPlus(): TextMateRule {
        this@TextMateLanguage.repository[this.internalName] = this
        return this
    }

    operator fun TextMateRule.invoke(): TextMateRule {
        val rule = this
        this@TextMateLanguage.patterns.add(TextMateRule("include_${rule.internalName}").apply {
            include = "#${rule.internalName}"
        })

        return this
    }

    fun dump(file: File) {
        jsonMapper {
            serializationInclusion(JsonInclude.Include.NON_EMPTY)
            this.addModule(SimpleModule().apply {
                addSerializer(UUID::class.java, UUIDSerializer())
                addSerializer(Regex::class.java, RegexSerializer)
            })
            enable(SerializationFeature.INDENT_OUTPUT)
        }.writeValue(file, this)
    }

}