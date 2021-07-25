import com.github.tetramad.kotlinnative.*
import com.github.tetramad.kwc.*

/**
 *  last modified 2021.07.25 by Tetramad
 */
fun main(/*args: Array<String>*/) {
    val path = "input.txt"
    val file = fileOpen(path, modes = "r")
    val content = file.map(::fileContent).getOrThrow()
    file.onSuccess(::fileClose)

    print("${lines(content)} ${words(content)} ${bytes(content)}")
}