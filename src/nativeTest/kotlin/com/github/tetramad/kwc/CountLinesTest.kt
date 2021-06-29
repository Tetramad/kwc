package com.github.tetramad.kwc

import kotlin.test.*

class CountLinesTest {
    @Test
    fun testEmptyString() {
        assertEquals(0, lines(""))
    }

    @Test
    fun testSingleSentenceWithNewline() {
        assertEquals(1, lines("Hello, world!\n"))
    }

    @Test
    fun testThreeSentencesWithoutLastNewline() {
        assertEquals(2, lines("lorem\nipsum\ndolor"))
    }

    @Test
    fun testTwoSentencesWithCarrageReturn() {
        assertEquals(2, lines("lorem\r\nipsum\r\n"))
    }
}
