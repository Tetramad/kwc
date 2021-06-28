package com.tetramad.wc

import kotlin.test.*

class CountCharactersTest{
    @Test
    fun testEmptyString() {
        assertEquals(0, bytes(""))
    }

    @Test
    fun testSingleCharacter() {
        assertEquals(1, bytes("A"))
    }

    @Test
    fun testSingleSentence() {
        assertEquals(13, bytes("Hello, world!"))
    }

    @Test
    fun testMultiEmptyLine() {
        assertEquals(3, bytes("\n\n\n"))
    }
    @Test
    fun testNonGlyphCharacter() {
        assertEquals(3, bytes(" \u0000 "))
    }
}

