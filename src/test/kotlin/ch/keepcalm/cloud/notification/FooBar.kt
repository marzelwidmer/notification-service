package ch.keepcalm.cloud.notification

import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

import java.net.URL

fun main(args: Array<String>) {
    println("Hello Dude..")



    GlobalScope.launch {
        delay(1000)
        println("Hello from Kotlin Coroutines!")
    }

    measureTime {
        val jokeIdList = listOf(1, 2, 3, 4, 5, 6, 7, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        for (jokeId in jokeIdList) {
            getJoke(jokeId.toString())
        }
    }

    // Let the main function running
    Thread.sleep(5000)
}


fun measureTime(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    val end = System.nanoTime()
    println("${(end - start) / 1.0e9} seconds")
}

fun getIp() {
    val result = URL("http://api.ipify.org").readText()
    println(result)
}

fun getJoke(jokeId: String = "random") {
    val result = URL("http://api.icndb.com/jokes/$jokeId").readText()
    println(result)
}


