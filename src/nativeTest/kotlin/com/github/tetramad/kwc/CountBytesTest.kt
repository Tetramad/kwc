package com.github.tetramad.kwc

import kotlin.test.*

class CountBytesTest {
    private fun setup(content: String) = ByteCounter().apply {
        content.forEach {
            consume(it)
        }
    }

    @Test
    fun testEmptyString() {
        assertEquals(0, setup("").count)
    }

    @Test
    fun testSingleByte() {
        assertEquals(1, setup(" ").count)
    }

    @Test
    fun testFiveBytes() {
        assertEquals(5, setup("Hello").count)
    }
    @Test
    fun testUnicode() {
        assertEquals(7, setup("안녕?").count)
    }
}
