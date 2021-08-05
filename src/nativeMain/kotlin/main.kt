import com.github.tetramad.kotlinnative.*
import com.github.tetramad.kwc.*
import kotlinx.cli.*

/**
 *  last modified at 2021.08.06 by Tetramad
 */
fun main(args: Array<String>) {
    val parser = ArgParser("kwc")
    val linesOpts by parser
        .option(ArgType.Boolean, "lines", "l", "print the newline counts")
        .multiple()
    val wordsOpts by parser
        .option(ArgType.Boolean, "words", "w", "print the word counts")
        .multiple()
    val charsOpts by parser
        .option(ArgType.Boolean, "chars", "m", "print the character counts")
        .multiple()
    val bytesOpts by parser
        .option(ArgType.Boolean, "bytes", "c", "print the byte counts")
        .multiple()
    val inputFiles by parser
        .argument(ArgType.String, "FILE", "...")
        .vararg()
    parser.parse(args)

    val counters = mutableListOf<Counter>().apply {
        if (linesOpts.isNotEmpty()) {
            add(LineCounter())
        }
        if (wordsOpts.isNotEmpty()) {
            add(WordCounter())
        }
        if (charsOpts.isNotEmpty()) {
            add(CharCounter())
        }
        if (bytesOpts.isNotEmpty()) {
            add(ByteCounter())
        }

        if (isEmpty()) {
            add(LineCounter())
            add(WordCounter())
            add(ByteCounter())
        }
    }

    val results = inputFiles.map(::readFile)
        .map { result ->
            result.map { content ->
                content.forEach { c ->
                    counters.forEach { counter ->
                        counter.consume(c)
                    }
                }
                counters.map { counter ->
                    counter.count.also {
                        counter.clear()
                    }
                }
            }
        }

    inputFiles.zip(results).forEach { (fileName, result) ->
        println(result.fold(
            { content -> "${content.joinToString(" ", transform = Int::toString)} $fileName" },
            { exception -> "${parser.programName}: $fileName: ${exception.message ?: "Unknown Error"}" }
        ))
    }
    if (results.size > 1) {
        val total = MutableList(counters.size) { 0 }
        results.forEach { result ->
            result.map {
                for (i in total.indices) {
                    total[i] += it[i]
                }
            }
        }
        println("${total.joinToString(" ", transform = Int::toString)} total")
    }
}
