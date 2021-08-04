package com.github.tetramad.kwc

import kotlin.test.*

class CountCharsTest {
    private fun setup(content: String) = CharCounter().apply {
        content.forEach {
            consume(it)
        }
    }

    @Test
    fun testEmptyString() {
        assertEquals(0, setup("").count)
    }

    @Test
    fun testSingleCharacter() {
        assertEquals(1, setup("A").count)
    }

    @Test
    fun testSingleSentence() {
        assertEquals(13, setup("Hello, world!").count)
    }

    @Test
    fun testMultiEmptyLine() {
        assertEquals(3, setup("\n\n\n").count)
    }
    @Test
    fun testNonGlyphCharacter() {
        assertEquals(3, setup(" \u0000 ").count)
    }
}
