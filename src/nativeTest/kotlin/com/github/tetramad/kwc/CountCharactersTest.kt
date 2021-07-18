package com.github.tetramad.kwc

import kotlin.test.*

class CountCharactersTest{
    @Test
    fun testEmptyString() {
        assertEquals(0, characters(""))
    }

    @Test
    fun testSingleCharacter() {
        assertEquals(1, characters("A"))
    }

    @Test
    fun testSingleSentence() {
        assertEquals(13, characters("Hello, world!"))
    }

    @Test
    fun testMultiEmptyLine() {
        assertEquals(3, characters("\n\n\n"))
    }
    @Test
    fun testNonGlyphCharacter() {
        assertEquals(3, characters(" \u0000 "))
    }
}

