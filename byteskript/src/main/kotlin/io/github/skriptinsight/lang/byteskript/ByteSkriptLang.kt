package io.github.skriptinsight.lang.byteskript

import io.github.skriptinsight.lang.byteskript.syntax.commentSyntax
import io.github.skriptinsight.lang.byteskript.syntax.constants.literals
import io.github.skriptinsight.lang.byteskript.syntax.keywordSyntax
import io.github.skriptinsight.lang.byteskript.syntax.sectionsSyntax
import io.github.skriptinsight.utils.dsl.textmate.language
import io.github.skriptinsight.utils.dsl.textmate.rule
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val language = language {
        name = "ByteSkript"
        scopeName = "source.byteskript"
        uuid = UUID.nameUUIDFromBytes("ByteSkript".toByteArray())

        val commentSyntax = commentSyntax()
        val keywordsSyntax = keywordSyntax()
        val literalSynax = literals()
        val sectionSynax = sectionsSyntax(literalSynax)

        val expressions = +rule("expressions") {
            literalSynax()
        }

        val code = +rule("code") {
            sectionSynax()
            keywordsSyntax()
            expressions()
            commentSyntax()
        }

        code()
    }
    language.dump(File(args[0]))
}