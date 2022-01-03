package io.github.skriptinsight.lang.byteskript.syntax.constants

import io.github.skriptinsight.utils.dsl.textmate.model.PatternContainer
import io.github.skriptinsight.utils.dsl.textmate.model.TextMateLanguage
import io.github.skriptinsight.utils.dsl.textmate.rule
import org.intellij.lang.annotations.Language

fun TextMateLanguage.literals() = +rule("literals") {
    val stringSyntax = this@literals.stringConstantSyntax()
    val simpleConstantsSyntax = this@literals.simpleConstants()
    val regexSyntax = this@literals.regexConstantSyntax()
    val numberSyntaxes = listOf(
        this@literals.numberSyntax("long", "-?\\d+[Ll](?![\\d.#DFdf])"),
        this@literals.numberSyntax("double", "-?\\d+(?:\\.\\d+)?[Dd]?(?![\\d.#FfLl])"),
        this@literals.numberSyntax("int", "-?\\d+(?![\\d.#LlFfDd])"),
        this@literals.numberSyntax("float", "-?\\d+(?:\\.\\d+)?[Ff](?![\\d.#DLdl])"),
    )

    stringSyntax()
    simpleConstantsSyntax()
    regexSyntax()
    numberSyntaxes.forEach { it() }
}

fun PatternContainer.numberSyntax(name: String, @Language("RegExp") syntax: String) = +rule(name) {
    match = syntax.toRegex()
    scopeName = "constant.numeric.decimal.byteskript"
}

fun PatternContainer.stringConstantSyntax() = +rule("string") {
    match = "\"[^\"\\\\\\r\\n]*(?:\\\\.[^\"\\\\\\r\\n]*)*\"".toRegex()
    scopeName = "string.quoted.double.byteskript"
}

fun PatternContainer.regexConstantSyntax() = +rule("regex") {
    match = "/[^/\\\\\\r\\n]+(?:\\\\.[^/\\\\\\r\\n]*)*/".toRegex()
    scopeName = "string.quoted.double.byteskript"
}

fun PatternContainer.simpleConstants() = +rule("simpleConstants") {
    match = "none|null|true|false".toRegex()
    scopeName = "constant.language.byteskript"
}