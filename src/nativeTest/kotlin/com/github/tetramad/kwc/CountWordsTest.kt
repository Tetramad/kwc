package com.github.tetramad.kwc

import kotlin.test.*

class CountWordsTest {
    @Test
    fun testEmptyString() {
        assertEquals(0, words(""))
    }

    @Test
    fun testSingleWord() {
        assertEquals(1, words("word"))
    }

    @Test
    fun testTwoWordsWithSingleSpace() {
        assertEquals(2, words("hello world"))
    }

    @Test
    fun testTwoWordsWithSingleNewline() {
        assertEquals(2, words("hello\nworld"))
    }

    @Test
    fun testSeveralWordsBetweenSingleWhitespace() {
        assertEquals(5, words("lorem ipsum\tdolor\nsit\ramet"))
    }

    @Test
    fun testTwoWordsBetweenSeveralWhitespace() {
        assertEquals(2, words("hello\n\t\r\n  \nworld"))
    }

    @Test
    fun testNonGlyphsInWords() {
        assertEquals(2, words("lorem ipsum\u0000dolor"))
    }
}
