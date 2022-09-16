import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    var res = withTimeoutOrNull(2000) { // This works same as withTimeout
        // the difference is that it doesn't throw an exception, and it returns
        // a value from coroutine in the form of lambda result if the execution
        // was successful if not i.e. it took more time than expected then it returns "null" instead
        for (i in 0..500) {
            print("$i.")
            delay(200)
        }
        "Yes!!!"
    }

    res = res ?: "No" // i.e. if res is null then "No" will be stored in it
    println()
    println("Did coroutine finished its work on time ? :$res ")
    println("Reached end of main function ")
}