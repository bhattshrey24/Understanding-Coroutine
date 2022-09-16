import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    try {
        withTimeout(2000) { // here we pass the time which we expect this coroutine
            // to take to finish its work. If it takes more than it then it will simply get cancelled,
            // and it will throwT "TimeoutCancellationException"
            for (i in 0..500) {
                print("$i.")
                delay(200)
            }
        }
    } catch (ex: TimeoutCancellationException) { //TimeoutCancellationException is subclass of CancellationException
        println()
        println("Coroutine was not able to finish the work in expected time!")
    }

    println()
    println("Reached end of main function ")
}