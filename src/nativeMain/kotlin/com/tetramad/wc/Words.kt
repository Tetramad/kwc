package com.tetramad.wc

// count every first non-whitespace characters.
// example
//content: lorem   ipsum   dolor  sit amet
//counts :01       2       3      4   5
//inWords:FT      FT      FT     FT  FT
fun words(content: String): Int {
    var isInWord = false
    var count = 0

    for (c in content) {
        count += if (!isInWord and !c.isWhitespace()) 1 else 0
        isInWord = !c.isWhitespace()
    }
    return count
}
