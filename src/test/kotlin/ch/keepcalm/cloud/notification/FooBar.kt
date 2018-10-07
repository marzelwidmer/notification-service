package ch.keepcalm.cloud.notification

import java.net.URL


fun main(args: Array<String>) {
    println("FooBar")

    measureTime {
        val jokeList = mutableListOf<String>()
        for (jokeId in 1..100) {
            jokeList += "Joke for $jokeId is ${getJoke(jokeId.toString())}"
        }
        for (joke in jokeList) {
            println(joke)
        }
        println("\n\n\n*************** caller IP is ${getIp()} ***************\n\n\n")
    }

    // Let the main function running
    Thread.sleep(30000)
}


private fun measureTime(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    val end = System.nanoTime()
    println("\n\n\n***************  ${(end - start) / 1.0e9} seconds ***************\n\n\n")
}


private fun getIp() =
        URL("http://api.ipify.org").readText()


private fun getJoke(jokeId: String = "random") =
        URL("http://api.icndb.com/jokes/$jokeId").readText()

