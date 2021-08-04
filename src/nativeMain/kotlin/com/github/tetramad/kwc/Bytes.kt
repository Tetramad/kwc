package com.github.tetramad.kwc

fun bytes(content: String): Int = content.encodeToByteArray().size

/**
 *  last modified at 2021.08.04 by Tetramad
 */
class ByteCounter : Counter() {
    override fun consume(c: Char) {
        count += c.toString().encodeToByteArray().size
    }
}
