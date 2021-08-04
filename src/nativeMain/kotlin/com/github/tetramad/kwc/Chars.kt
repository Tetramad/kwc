package com.github.tetramad.kwc

fun chars(content: String): Int = content.length

/**
 *  last modified at 2021.08.04 by Tetramad
 */
class CharCounter : Counter() {
    override fun consume(c: Char) {
        count += 1
    }
}
