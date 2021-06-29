import kotlinx.cinterop.*
import platform.posix.*
import com.github.tetramad.kwc.*

fun main(/*args: Array<String>*/) {
    val file: CPointer<FILE>? = fopen("input.txt", "r")
    if (file != null) {
        fseek(file, 0, SEEK_END) // TODO:: fseek() returns result value type of int
        val fileSize = ftell(file)
        rewind(file)
        var content = ""
        memScoped {
            val arr = allocArray<ByteVar>(fileSize)
            fread(arr, sizeOf<ByteVar>().toULong(), fileSize.toULong(), file)
            content = arr.toKString()
        }
        fclose(file)
        print("${lines(content)} ${words(content)} ${bytes(content)}")
    } else {
        print("Can't open file")
    }
}