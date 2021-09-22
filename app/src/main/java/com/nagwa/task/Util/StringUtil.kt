package com.nagwa.task.Util

import kotlin.random.Random


private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
fun randomName(): String {
    return (1..10)
        .map { i -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}