package me.ugreek85.utils

import java.util.*
import kotlin.random.Random

object KeyGenerator {

    fun generateKey(uuid: UUID, bytesCount: Int): ByteArray
    {
        val random= Random(uuid.mostSignificantBits xor uuid.leastSignificantBits)
        val bytes = ByteArray(bytesCount)
        random.nextBytes(bytes)
        return bytes
    }
}