package ch.keepcalm.cloud.notification

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.net.URL


fun main(args: Array<String>) {
    println("FooBarAsyc")

    GlobalScope.launch {
        println("Hello from Kotlin Coroutines!")

        measureTime {
            val jokeListDeferred = mutableListOf<Deferred<String>>()
            for (jokeId in 1..100) {
                jokeListDeferred += async { "Joke for $jokeId is ${getJoke(jokeId.toString())}" }
            }
            for (joke in jokeListDeferred) {
                println(joke.await())
            }

            println("\n\n\n*************** caller IP is ${getIp()} ***************\n\n\n")
        }
    }


    // Let the main function running
    Thread.sleep(30000)
}

private suspend fun measureTime(block: suspend () -> Unit) {
    val start = System.nanoTime()
    block()
    val end = System.nanoTime()
    println("\n\n\n***************  ${(end - start) / 1.0e9} seconds ***************\n\n\n")
}


private fun getIp() =
        URL("http://api.ipify.org").readText()

private fun getJoke(jokeId: String = "random") =
        URL("http://api.icndb.com/jokes/$jokeId").readText()
