import com.github.tetramad.kotlinnative.*
import com.github.tetramad.kwc.*
import kotlin.math.max
import kotlinx.cli.*

/**
 *  last modified 2021.08.04 by Tetramad
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

    val nullOpt = linesOpts.isEmpty() and wordsOpts.isEmpty() and charsOpts.isEmpty() and bytesOpts.isEmpty()
    val linesOpt = if (nullOpt) { true } else { linesOpts.isNotEmpty() }
    val wordsOpt = if (nullOpt) { true } else { wordsOpts.isNotEmpty() }
    val charsOpt = if (nullOpt) { false } else { charsOpts.isNotEmpty() }
    val bytesOpt = if (nullOpt) { true } else { bytesOpts.isNotEmpty() }

    val results = inputFiles.map {
        val file = fileOpen(it, modes = "r")
        val content = file.map(::fileContent)
        file.onSuccess(::fileClose)
        content
    }.map {
        it.map { content ->
            val linesString = if (linesOpt) {
                lines(content).toString().run {
                    padStart(max(0, 3 - length), ' ')
                }
            } else {
                ""
            }
            val wordsString = if (wordsOpt) {
                words(content).toString().run {
                    padStart(max(0, 3 - length), ' ')
                }
            } else {
                ""
            }
            val charsString = if (charsOpt) {
                characters(content).toString().run {
                    padStart(max(0, 3 - length), ' ')
                }
            } else {
                ""
            }
            val bytesString = if (bytesOpt) {
                bytes(content).toString().run {
                    padStart(max(0, 3 - length), ' ')
                }
            } else {
                ""
            }
            "$linesString$wordsString$charsString$bytesString"
        }
    }

    inputFiles.zip(results).forEach { (fileName, result) ->
        println(result.fold(
            { content -> "${content} ${fileName}" },
            { exception -> "${parser.programName}: ${fileName}: ${exception.message ?: "Unknown Error"}" }
        ))
    }
}