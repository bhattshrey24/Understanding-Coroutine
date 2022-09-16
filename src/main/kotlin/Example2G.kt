import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    val job: Job = launch {
        try { // when coroutine gets canceled then "delay()" function throws
            // CancellationException exception since it is a part of “kotlin.coroutines” package
            for (i in 0..500) {
                print("$i.")
                delay(50)
            }
        } catch (ex: CancellationException) {
            println()
            println("Safely caught CancellationException with message ${ex.message} ")
        } finally {
            println("Cannot run suspending functions on this coroutine now!")// you cannot run
            // suspending function in finally block because by the time we reached here the
            // coroutine is already terminated but if you really want to run a suspending
            // function from a "finally" block then wrap the code
            // within withContext(NonCancellable) function
            withContext(NonCancellable) { // this is another "coroutine builder"
                // so it will start a new coroutine in another "context"
                println("Inside withContext(NonCancellable)")
                delay(4000)
                println("Exiting withContext(NonCancellable)")
            }
        }

    }
    delay(2000)
    job.cancel(CancellationException("My exception message!!!!"))// we can even pass our
    // own exception message like this
    job.join()
    println()

    println("Reached end of main function ")
}