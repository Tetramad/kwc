package com.github.tetramad.kwc

import kotlin.test.*

class CountWordsTest {
    private fun setup(content: String) = WordCounter().apply {
        content.forEach {
            consume(it)
        }
    }

    @Test
    fun testEmptyString() {
        assertEquals(0, setup("").count)
    }

    @Test
    fun testSingleWord() {
        assertEquals(1, setup("word").count)
    }

    @Test
    fun testTwoWordsWithSingleSpace() {
        assertEquals(2, setup("hello world").count)
    }

    @Test
    fun testTwoWordsWithSingleNewline() {
        assertEquals(2, setup("hello\nworld").count)
    }

    @Test
    fun testSeveralWordsBetweenSingleWhitespace() {
        assertEquals(5, setup("lorem ipsum\tdolor\nsit\ramet").count)
    }

    @Test
    fun testTwoWordsBetweenSeveralWhitespace() {
        assertEquals(2, setup("hello\n\t\r\n  \nworld").count)
    }

    @Test
    fun testNonGlyphsInWords() {
        assertEquals(2, setup("lorem ipsum\u0000dolor").count)
    }
}
