package ch.keepcalm.cloud.notification

import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import java.net.URL



fun main(args: Array<String>) {
    GlobalScope.launch {
        println("Hello from Kotlin Coroutines!")

        measureTime {
            for (jokeId in 1..604) {
                println(getJoke(jokeId.toString()))
                //val joke = Gson().fromJson(result, IcndbJoke::class.java)
                //println(joke.value?.joke)
            }
        }
    }

    // Let the main function running
    Thread.sleep(70000)
}


fun measureTime(block: () -> Unit) {
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




