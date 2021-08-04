package com.github.tetramad.kwc

/**
 *  last modified at 2021.08.05 by Tetramad
 */
@Deprecated(
    "Use CharCounter() class with consume method and count property.",
    ReplaceWith("CharCounter().apply { content.forEach { consume(it) } }.count")
)
fun chars(content: String): Int = content.length

/**
 *  last modified at 2021.08.04 by Tetramad
 */
class CharCounter : Counter() {
    override fun consume(c: Char) {
        count += 1
    }
}
