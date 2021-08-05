package com.github.tetramad.kwc

/**
 *  last modified at 2021.08.06 by Tetramad
 */
abstract class Counter {
    var count: Int = 0
        protected set

    abstract fun consume(c: Char)
    fun clear() {
        count = 0
    }
}
