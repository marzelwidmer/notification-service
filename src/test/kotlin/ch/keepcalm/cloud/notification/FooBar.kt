package ch.keepcalm.cloud.notification

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.net.URL


fun main(args: Array<String>) {


    GlobalScope.launch {
        println("Hello from Kotlin Coroutines!")

        measureTime {
            val jokeListDeferred = mutableListOf<Deferred<String>>()
            for (jokeId in 1..200) {
                jokeListDeferred += async { "Joke for $jokeId is ${getJoke(jokeId.toString())}" }
            }
            for (joke in jokeListDeferred) {
                println(joke.await())
            }
        }


        measureTime {
            val jokeList  = mutableListOf<String>()
            for (jokeId in 1..200) {
                jokeList += "Joke for $jokeId is ${getJoke(jokeId.toString())}"
            }
            for (joke in jokeList) {
                println(joke)
            }
        }
    }

    // Let the main function running
    Thread.sleep(70000)
}


suspend fun measureTime(block: suspend () -> Unit) {
    val start = System.nanoTime()
    block()
    val end = System.nanoTime()
    println("***************  ${(end - start) / 1.0e9} seconds ***************  ")
}






fun getIp() {
    val result = URL("http://api.ipify.org").readText()
    println(result)
}


fun getJoke(jokeId: String = "random") =
        URL("http://api.icndb.com/jokes/$jokeId").readText()




