package io.github.skriptinsight.lang.byteskript.syntax

import io.github.skriptinsight.utils.dsl.textmate.model.PatternContainer
import io.github.skriptinsight.utils.dsl.textmate.model.TextMateRule
import io.github.skriptinsight.utils.dsl.textmate.rule

fun PatternContainer.sectionsSyntax(literalSyntax: TextMateRule) = +rule("sections") {
    +rule("returnSection") {
        match = "(return:)\\s*([^ ]+)".toRegex()

        capture("1") {
            scopeName = "storage.type.section byteskript.section.return"
        }

        capture("2") {
            scopeName = "byteskript.section.return.type storage.type.java"
        }
    }

    singleWordSection()
    multiWordSection(literalSyntax)
}

fun PatternContainer.multiWordSection(literalSyntax: TextMateRule) = +rule("singleWordSection") {
    match = "([^: ]+) ([^:]+):".toRegex()
    scopeName = "byteskript.section.multiword"

    capture("0") {
        scopeName = "byteskript.section.multiword.zero"

        +rule("bigSection") bigRule@{
            val introKeywords = listOf("on", "loop", "else", "else if")
            match = "(?:${introKeywords.joinToString("|")}) [^:]+".toRegex()
            scopeName = "storage.type.section byteskript.section.multiword.intro"
        }
        this@multiWordSection.functionSyntax().invoke()
        this@multiWordSection.keywordSyntax().invoke()
        this@multiWordSection.colonSyntax().invoke()
        literalSyntax()
    }
}

private fun PatternContainer.colonSyntax() = +rule("colon") {
    match = ":".toRegex()
    scopeName = "variable.other.object byteskript.section.multiword.colon"
}

fun PatternContainer.singleWordSection() = +rule("singleWordSection") {
    match = "(?:[^: ]+)(:)".toRegex()
    scopeName = "storage.type.section byteskript.section.singleword"
    capture("1") {
        scopeName = "variable.other.object byteskript.section.singleword.colon"
    }
}

fun PatternContainer.functionSyntax() = +rule("function") {
    match = "(function) ([^ (,]+)\\s*(?:(\\()(@?[^ (,]+(?:,\\s+@?[^ (,]+)*)(\\)))?".toRegex()
    scopeName = "storage.type.section byteskript.section.function"


    capture("1") {
        scopeName = "keyword.control.byteskript"
    }
    capture("2") {
        scopeName = "entity.name.function.byteskript"
    }

    arrayOf(3, 5).forEach {
        capture(it.toString()) {
            scopeName = "storage.type.section byteskript.section.function.parenthesis"
        }
    }

    capture("4") {
        +rule("arg") {
            match = "[^, ]+".toRegex()
            scopeName = "variable.parameter.byteskript"
        }
    }

    colonSyntax()

}