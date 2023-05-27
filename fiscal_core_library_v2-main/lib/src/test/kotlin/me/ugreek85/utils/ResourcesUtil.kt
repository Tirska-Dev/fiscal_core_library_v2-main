package me.ugreek85.utils

import java.io.File


object ResourcesUtil {

    private const val rootDir = "src/test/resources"

    fun readBytes(fileName: String): ByteArray
    {
        println(File(rootDir, fileName).absolutePath)
        return getResource(fileName).readBytes()
    }

    fun getResource(fileName: String): File{
        return File(rootDir, fileName)
    }
}
