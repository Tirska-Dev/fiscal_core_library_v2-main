package me.ugreek85.utils

import java.util.zip.CRC32

object Crc32 {

    fun get(bytes: ByteArray): Int
    {
        val crc32 = CRC32()
        crc32.update(bytes)
        return Integer.reverseBytes(crc32.value.toInt())
    }
}