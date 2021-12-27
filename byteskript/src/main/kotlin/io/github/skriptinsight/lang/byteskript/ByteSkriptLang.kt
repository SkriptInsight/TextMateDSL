package io.github.skriptinsight.lang.byteskript

import io.github.skriptinsight.utils.dsl.textmate.language
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val language = language {
        name = "ByteSkript"
        scopeName = "source.byteskript"
        uuid = UUID.nameUUIDFromBytes("ByteSkript".toByteArray())
    }

    language.dump(File(args[0]))
}