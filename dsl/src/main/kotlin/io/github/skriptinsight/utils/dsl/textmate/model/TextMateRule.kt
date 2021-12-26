package io.github.skriptinsight.utils.dsl.textmate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.github.skriptinsight.utils.dsl.textmate.annotations.LanguageDslMarker

@LanguageDslMarker
data class TextMateRule(@JsonIgnore val internalName: String) {
    var include: String? = null
    var comment: String? = null

    var begin: Regex? = null
    var end: Regex? = null

    var match: Regex? = null

    var patterns = mutableListOf<TextMateRule>()

    @JsonProperty("name")
    var scopeName: String? = null

    operator fun String.unaryMinus() {
        this@TextMateRule.comment = this
    }

}