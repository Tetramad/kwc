package com.github.tetramad.kotlinnative

import kotlinx.cinterop.*
import platform.posix.*

/**
 *  last modified at 2021.08.06 by Tetramad
 *
 *  fileOpen should return Result of C style file pointer.
 */
@Deprecated("Use readFile() function.")
fun fileOpen(path: String, modes: String /* or enum type */): Result<CPointer<FILE>> {
    val file: CPointer<FILE>? = fopen(path, modes)
    return if (file == null) {
        Result.failure(Throwable(strerror(errno)?.toKString() ?: "Unknown Error"))
    } else {
        Result.success(file)
    }
}

/**
 *  last modified at 2021.08.06 by Tetramad
 *
 *  fileClose just wrapped fclose.
 */
@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use readFile() function.")
fun fileClose(file: CPointer<FILE>) {
    fclose(file)
}

/**
 *  last modified at 2021.08.06 by Tetramad
 *
 *  fileContent should return all contents of file as String.
 */
@Deprecated("Use readFile() function.")
fun fileContent(file: CPointer<FILE>): String {
    val size = fileSize(file)
    var content = ""
    memScoped {
        val arr = allocArray<ByteVar>(size)
        fread(arr, sizeOf<ByteVar>().toULong(), size.toULong(), file)
        content = arr.toKString()
    }
    return content
}

/**
 *  last modified at 2021.08.06 by Tetramad
 *
 *  This function should return file size in byte.
 *  1. Seek file end
 *  2. Read fd position information
 *  3. Assume that is file size
 *  4. Don't forget put it back to original fd position information
 */
@Deprecated("Not Useful Now")
internal fun fileSize(file: CPointer<FILE>): Int {
    val savedPosition = ftell(file)
    fseek(file, 0, SEEK_END)
    val size = ftell(file).toInt()
    fseek(file, savedPosition, SEEK_SET)
    rewind(file)
    return size
}

/**
 *  last modified at 2021.08.06 by Tetramad
 */
fun readFile(path: String): Result<String> {
    val file = fopen(path, "r")
        ?: return Result.failure(Throwable(strerror(errno)?.toKString() ?: "Failed to open file. Unknown cause."))

    val list = mutableListOf<String>()
    val bufferSize = 1024
    do {
        memScoped {
            val buffer = allocArray<ByteVar>(bufferSize + 1)
            val last = fread(buffer, sizeOf<ByteVar>().toULong(), bufferSize.toULong(), file)
            buffer[last.toLong()] = 0.toByte()
            list.add(buffer.toKString())
        }
    } while (feof(file) == 0 && ferror(file) == 0)
    return if (ferror(file).also { fclose(file) } != 0) {
        Result.failure(Throwable(strerror(errno)?.toKString() ?: "Failed to read file. Unknown cause."))
    } else {
        Result.success(list.joinToString(""))
    }
}