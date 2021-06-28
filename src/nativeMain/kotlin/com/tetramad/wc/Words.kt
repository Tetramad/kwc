package com.tetramad.wc

fun words(content: String): Int {
    fun Pair<Int, Boolean>.counts(): Int = this.first
    fun Pair<Int, Boolean>.isInWords(): Boolean = this.second

    return content.fold(0 to false) { state, c -> when {
        state.isInWords() && c.isWhitespace() -> state.counts() to false
        state.isInWords() && !c.isWhitespace() -> state.counts() to true
        !state.isInWords() && c.isWhitespace() -> state.counts() to false
        !state.isInWords() && !c.isWhitespace() -> state.counts() + 1 to true
        else -> state
    }}.counts()
}
