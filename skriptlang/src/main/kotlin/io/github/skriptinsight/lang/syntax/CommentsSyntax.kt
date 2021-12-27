package io.github.skriptinsight.lang.syntax

import io.github.skriptinsight.utils.dsl.textmate.model.TextMateLanguage
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.COMMENT_LINE

fun TextMateLanguage.commentSyntax() = +rule("comments") {
    -"Comment syntax for Skript"
    begin = Regex("#")
    end = Regex("\$")

    scopeName = COMMENT_LINE
}