package com.github.tetramad.kwc

/**
 *  last modified at 2021.08.05 by Tetramad
 */
@Deprecated(
    "Use ByteCounter() class with consume method and count property.",
    ReplaceWith("ByteCounter().apply { content.forEach { consume(it) } }.count")
)
fun bytes(content: String): Int = content.encodeToByteArray().size

/**
 *  last modified at 2021.08.04 by Tetramad
 */
class ByteCounter : Counter() {
    override fun consume(c: Char) {
        count += c.toString().encodeToByteArray().size
    }
}
