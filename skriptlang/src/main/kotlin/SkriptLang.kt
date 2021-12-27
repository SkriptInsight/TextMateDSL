
import io.github.skriptinsight.lang.syntax.commentSyntax
import io.github.skriptinsight.lang.syntax.constantSyntax
import io.github.skriptinsight.lang.syntax.variableSyntax
import io.github.skriptinsight.utils.dsl.textmate.language
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.KEYWORD_OTHER
import java.io.File
import java.util.*

fun main(args: Array<String>) {

    val skriptLang = language {
        name = "SkriptLang"
        scopeName = "source.skriptlang"
        uuid = UUID.fromString("bdf45b93-232e-475f-8783-cad8f0a82772")

        val comments = commentSyntax()
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
        }

        val constants = constantSyntax(evaluated)

        val code = +rule("code") {
            -"Code syntax for Skript"

            constants()
            variables()
            evaluated()
            comments()
        }

        code()
    }

    skriptLang.dump(File(args[0]))

}

