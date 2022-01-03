package io.github.skriptinsight.lang.byteskript.syntax

import io.github.skriptinsight.utils.dsl.textmate.model.PatternContainer
import io.github.skriptinsight.utils.dsl.textmate.rule

val keywords = setOf(
    "else",
    "if",
    "while",
    "run",
    "return",
    "print",
    "set",
    "in",
    "is",
    "to",
    "type",
    "template",
    "final",
    "local",
    "set",
    "add",
    "delete",
    "remove",
    "try",
    "catch",
    "loop",
    "throw",
    "return",
    "break",
    "continue",
    "stop",
    "exit",
    "assert",
    "new",
)

fun PatternContainer.keywordSyntax() = +rule("keywords") {
    match = "\\b${keywords.joinToString("|")}\\b".toRegex()
    scopeName = "keyword.control.byteskript"
}