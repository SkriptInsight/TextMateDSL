
import io.github.skriptinsight.utils.dsl.textmate.language
import io.github.skriptinsight.utils.dsl.textmate.rule
import io.github.skriptinsight.utils.dsl.textmate.utils.COMMENT_LINE
import java.io.File
import java.util.*

fun main() {

    val skriptLang = language {
        scopeName = "source.skriptlang"
        uuid = UUID.fromString("bdf45b93-232e-475f-8783-cad8f0a82772")

        val comments = +rule("comments") {
            -"Comment syntax for Skript"
            begin = Regex("#")
            end = Regex("\$")

            scopeName = COMMENT_LINE
        }

        comments()

    }

    skriptLang.dump(File("skriptlang.json"))

}

