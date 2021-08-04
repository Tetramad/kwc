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

/**
 *  last modified at 2021.08.04 by Tetramad
 */
class WordCounter : Counter() {
    var isInWord: Boolean = false

    override fun consume(c: Char) {
        when {
            isInWord and c.isWhitespace() -> {
                isInWord = false
            }
            !isInWord and !c.isWhitespace() -> {
                isInWord = true
                count += 1
            }
        }
    }
}
