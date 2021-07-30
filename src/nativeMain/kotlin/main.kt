import com.github.tetramad.kotlinnative.*
import com.github.tetramad.kwc.*
import kotlin.math.max

/**
 *  last modified 2021.07.30 by Tetramad
 */
fun main(args: Array<String>) {
    val programName = "kwc"
    val results = args.map {
        val file = fileOpen(it, modes = "r")
        val content = file.map(::fileContent)
        file.onSuccess(::fileClose)
        content
    }.map {
        it.map { content ->
            var linesString = lines(content).toString()
            linesString = linesString.padStart(max(0, 3 - linesString.length), ' ')
            var wordsString = words(content).toString()
            wordsString = wordsString.padStart(max(0, 3 - wordsString.length), ' ')
            var bytesString = bytes(content).toString()
            bytesString = bytesString.padStart(max(0, 3 - bytesString.length), ' ')
            "$linesString $wordsString $bytesString" }
    }

    args.zip(results).forEach { (fileName, result) ->
        println(result.fold(
            { content -> "${content} ${fileName}" },
            { exception -> "${programName}: ${fileName}: ${exception.message ?: "Unknown Error"}" }
        ))
    }
}