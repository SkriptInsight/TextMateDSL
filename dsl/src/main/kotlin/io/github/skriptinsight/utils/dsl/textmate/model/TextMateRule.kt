package io.github.skriptinsight.utils.dsl.textmate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.github.skriptinsight.utils.dsl.textmate.annotations.LanguageDslMarker

@LanguageDslMarker
data class TextMateRule(@JsonIgnore val internalName: String) : PatternContainer {
    var include: String? = null
    var comment: String? = null

    var begin: Regex? = null
    val beginCaptures = mutableMapOf<String, TextMateRule>()
    var end: Regex? = null
    val endCaptures = mutableMapOf<String, TextMateRule>()

    var match: Regex? = null

    override val patterns = mutableListOf<TextMateRule>()
    override val captures: MutableMap<String, TextMateRule> = mutableMapOf()

    @JsonProperty("name")
    var scopeName: String? = null

    fun beginCapture(groupName: String, builder: TextMateRule.() -> Unit): TextMateRule {
        val rule = TextMateRule("beginCapture_$groupName")
        rule.builder()
        this.beginCaptures[groupName] = rule
        return rule
    }

    fun beginCapture(groupName: String, rule: TextMateRule): TextMateRule {
        this.beginCaptures[groupName] = rule
        return rule
    }

    fun endCapture(groupName: String, builder: TextMateRule.() -> Unit): TextMateRule {
        val rule = TextMateRule("endCapture_$groupName")
        rule.builder()
        this.endCaptures[groupName] = rule
        return rule
    }

    fun endCapture(groupName: String, rule: TextMateRule): TextMateRule {
        this.endCaptures[groupName] = rule
        return rule
    }

    operator fun String.unaryMinus() {
        this@TextMateRule.comment = this
    }

}