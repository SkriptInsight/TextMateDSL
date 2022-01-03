package io.github.skriptinsight.lang.byteskript.syntax

import io.github.skriptinsight.utils.dsl.textmate.model.TextMateLanguage
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.COMMENT_LINE

fun TextMateLanguage.commentSyntax() = +rule("comment") {
    begin = "//".toRegex()
    end = "$".toRegex()
    scopeName = COMMENT_LINE
}