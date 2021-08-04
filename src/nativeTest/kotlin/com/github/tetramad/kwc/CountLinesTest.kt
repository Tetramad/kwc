package com.github.tetramad.kwc

import kotlin.test.*

class CountLinesTest {
    private fun setup(content: String) = LineCounter().apply {
        content.forEach {
            consume(it)
        }
    }

    @Test
    fun testEmptyString() {
        assertEquals(0, setup("").count)
    }

    @Test
    fun testSingleSentenceWithNewline() {
        assertEquals(1, setup("Hello, world!\n").count)
    }

    @Test
    fun testThreeSentencesWithoutLastNewline() {
        assertEquals(2, setup("lorem\nipsum\ndolor").count)
    }

    @Test
    fun testTwoSentencesWithCarrageReturn() {
        assertEquals(2, setup("lorem\r\nipsum\r\n").count)
    }
}
