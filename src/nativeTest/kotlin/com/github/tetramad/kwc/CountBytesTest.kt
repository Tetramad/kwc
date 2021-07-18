package com.github.tetramad.kwc

import kotlin.test.*

class CountBytesTest {
    @Test
    fun testEmptyString() {
        assertEquals(0, bytes(""))
    }

    @Test
    fun testSingleByte() {
        assertEquals(1, bytes(" "))
    }

    @Test
    fun testFiveBytes() {
        assertEquals(5, bytes("Hello"))
    }
    @Test
    fun testUnicode() {
        assertEquals(7, bytes("안녕?"))
    }
}