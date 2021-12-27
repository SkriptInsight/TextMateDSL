package io.github.skriptinsight.utils.dsl.textmate

import io.github.skriptinsight.utils.dsl.textmate.model.TextMateLanguage
import io.github.skriptinsight.utils.dsl.textmate.model.TextMateRule

fun language(builder: TextMateLanguage.() -> Unit): TextMateLanguage {
    val language = TextMateLanguage()
    language.builder()
    return language
}

fun rule(internalName: String, builder: TextMateRule.() -> Unit): TextMateRule {
    val rule = TextMateRule(internalName)
    rule.builder()
    return rule
}
