package com.github.tetramad.kwc

/**
 *  last modified at 2021.08.05
 */
@Deprecated(
    "Use LineCounter() class with consume method and count property.",
    ReplaceWith("LineCounter().apply { content.forEach { consume(it) } }.count")
)
fun lines(content: String): Int = content.count { it == '\n' }

/**
 *  last modified at 2021.08.04 by Tetramad
 */
class LineCounter : Counter() {
    override fun consume(c: Char) {
        if (c == '\n') {
            count += 1
        }
    }
}
