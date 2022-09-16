import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    val job: Job = launch {
        for (i in 0..500) {
            print("$i.")
            yield() //It doesn't delay your coroutine and also makes it cooperate
            // because it is a part
            // of “kotlin.coroutines” package
        }

    }
    delay(11)
    job.cancelAndJoin()
    println()
    println("Reached end of main function ")

}