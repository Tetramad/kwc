package com.github.tetramad.kwc

// count every first non-whitespace characters.
// example
//content: lorem   ipsum   dolor  sit amet
//counts :01       2       3      4   5
//inWords:FT      FT      FT     FT  FT
fun words(content: String): Int {
    fun Pair<Boolean, Int>.isInWords(): Boolean = this.first
    fun Pair<Boolean, Int>.counts(): Int = this.second

    return content.fold(Pair(false, 0)) { state, c -> when {
        state.isInWords() and c.isWhitespace() -> Pair(false, state.counts())
        !state.isInWords() and !c.isWhitespace() -> Pair(true, state.counts() + 1)
        else -> state
    }}.counts()
}
