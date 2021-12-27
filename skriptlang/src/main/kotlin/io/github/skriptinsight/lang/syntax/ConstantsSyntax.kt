package io.github.skriptinsight.lang.syntax

import io.github.skriptinsight.lang.model.ChatColors
import io.github.skriptinsight.utils.dsl.textmate.model.TextMateLanguage
import io.github.skriptinsight.utils.dsl.textmate.model.TextMateRule
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.NUMERIC_CONSTANT
import java.util.*

fun TextMateLanguage.constantSyntax(evaluatedValues: TextMateRule) = +rule("constants") {
    -"Skript Constants"
    val stringSyntax = this@constantSyntax.stringConstantSyntax(evaluatedValues)
    val numberSyntax = this@constantSyntax.numberSyntax()

    stringSyntax()
    numberSyntax()
}

fun TextMateLanguage.numberSyntax() = +rule("numbers") {
    -"Skript Numbers"

    match = Regex("-?[0-9]+(?>\\.[0-9]+)?%?")

    scopeName = NUMERIC_CONSTANT
}

fun TextMateLanguage.stringConstantSyntax(evaluatedValues: TextMateRule) = +rule("string") {
    -"Skript Strings"
    scopeName = "string.quoted.double.skript"
    match = Regex("\"([^\"]*?(?:\"\"[^\"]*)*)\"")

    capture("1") {
        evaluatedValues()

        ChatColors.values().forEach {
            val colorName = it.name.lowercase(Locale.getDefault())
            +rule("chatcolor_$colorName") {
                -"Chat color: ${it.name}"
                scopeName = "constant.other.color.skript.$colorName"
                match = Regex("(?:[&§]${it.char}|${it.names.joinToString("|") { "<$it>" }})")
            }
        }
    }
}