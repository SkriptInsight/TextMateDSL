
import io.github.skriptinsight.lang.syntax.constantSyntax
import io.github.skriptinsight.lang.syntax.optionVariableSyntax
import io.github.skriptinsight.lang.syntax.variableSyntax
import io.github.skriptinsight.utils.dsl.textmate.language
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.COMMENT_LINE
import io.github.skriptinsight.utils.dsl.textmate.utils.KEYWORD_OTHER
import java.io.File
import java.util.*

fun main(args: Array<String>) {

    val skriptLang = language {
        scopeName = "source.skriptlang"
        uuid = UUID.fromString("bdf45b93-232e-475f-8783-cad8f0a82772")

        val comments = +rule("comments") {
            -"Comment syntax for Skript"
            begin = Regex("#")
            end = Regex("\$")

            scopeName = COMMENT_LINE
        }

        val optionVariables = optionVariableSyntax()
        val variables = variableSyntax()

        val evaluated = +rule("evaluated") {
            -"Evaluated variables"
            scopeName = "storage.type.skript"

            begin = Regex("%")
            beginCapture("0") {
                scopeName = KEYWORD_OTHER
            }

            end = Regex("%")
            endCapture("0") {
                scopeName = KEYWORD_OTHER
            }

            variables()
            optionVariables()
        }

        val constants = constantSyntax(evaluated)

        val code = +rule("code") {
            -"Code syntax for Skript"

            constants()
            variables()
            evaluated()
            optionVariables()
            comments()
        }

        code()
    }

    skriptLang.dump(File(args[0]))

}

