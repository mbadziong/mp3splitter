import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.system.exitProcess

data class ParsedArgs(
    val file: File,
    val expectedParts: Int
)

fun splitToTracks(parsedArgs: ParsedArgs) {
    val totalLength = parsedArgs.file.length().also { println("total size is ~${it/1024/1024}MB") }
    val splitStep = (totalLength / parsedArgs.expectedParts).also { println("chunk size will be ~${it/1024/1024}MB") }

    FileInputStream(parsedArgs.file).use {
        for (chunkNumber in 1..parsedArgs.expectedParts) {
            val chunk = ByteArray(splitStep.toInt())
            val currentChunkPath = "${parsedArgs.file.parent}\\$chunkNumber-${parsedArgs.file.name}"
            val readBytes = it.read(chunk)
            with(FileOutputStream(currentChunkPath)) {
                write(chunk, 0, readBytes)
                close()
            }
            println("created file $currentChunkPath")
        }
    }
}

fun parseArguments(filePath: String?, count: String?): ParsedArgs {
    val file = File(filePath ?: "")
    if (!file.exists() || file.isDirectory) {
        println("File doesn't exist or is a directory")
        exitProcess(1)
    }

    val expectedParts = Integer.parseInt(count)
    if (expectedParts < 2) {
        println("expected output files count should be greater than 1")
        exitProcess(1)
    }
    return ParsedArgs(file, expectedParts).also { println("preparing to divide ${it.file} into ${it.expectedParts} parts") }
}

when (args.size) {
    0 -> println("Missing input file and expected output files count")
    1 -> println("Missing input file or expected output files count")
    else -> splitToTracks(parseArguments(args[0], args[1]))
}