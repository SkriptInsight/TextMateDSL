package io.github.skriptinsight.lang.syntax

import io.github.skriptinsight.utils.dsl.textmate.model.TextMateLanguage
import io.github.skriptinsight.utils.dsl.textmate.model.TextMateRule
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.LANGUAGE_CONSTANT

fun TextMateLanguage.optionVariableSyntax() = +rule("optionVariables") {
    -"Options variable syntax"

    match = Regex("\\{@[^}]+}")
    scopeName = LANGUAGE_CONSTANT
}

fun TextMateLanguage.simpleVariableSyntax() = +rule("variable") {
    -"Simple variable syntax"
    scopeName = "variable.other.skript"
    begin = Regex("(?:(?:the )?var(?:iable)? )?\\{")
    end = Regex("}")

    "evaluated"()
}

fun TextMateLanguage.variableSyntax(): TextMateRule {
    val optionVariables = optionVariableSyntax()
    val simpleVariables = simpleVariableSyntax()

    return +rule("variables") {
        -"Rule with all Skript variables"

        optionVariables()
        simpleVariables()
    }
}