import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts on thread : ${Thread.currentThread().name}")


    var time = measureTimeMillis { // this measures how much time the code inside it's lambda
        // to execute
        val msg1: Deferred<String> = async { getMessageOne() } // launches a new child
        // coroutine in which getMessageOne() is executed
        val msg2: Deferred<String> = async { getMessageTwo() } // launches a new child
        // coroutine in which getMessageTwo() is executed

        println("The complete message is : ${msg1.await() + msg2.await()}")
    }


    println("Completed in time $time ms")// Observe the time is 4 seconds and not 6
    // because both getMessageOne() and getMessageOne() are run by different coroutine and hence
    // worked parallel. So basically it took time equal to which ever took more time between
    // getMessageOne() and getMessageTwo() because in first 2 seconds getMessageOne() completed its execution
    // whereas half of getMessageTwo() execution was finished so 2 seconds more to go.


    println("Reached end of main function ")
}

suspend fun getMessageOne(): String {
    delay(4000L)// pretending to do some work
    return "Hello"
}

suspend fun getMessageTwo(): String {
    delay(2000L)// pretending to do some work
    return "World"
}