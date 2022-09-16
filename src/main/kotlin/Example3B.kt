import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts on thread : ${Thread.currentThread().name}")


    val msg1: Deferred<String> = async(start = CoroutineStart.LAZY) // What this does
    // is that it makes the coroutine lazy ie. now the body of this(ie. getMessageThree())
    // will only be executed
    // when "msg1" is needed somewhere otherwise it will wait. Now OBSERVER that this program won't
    // stop/terminate unless msg1 is used somewhere or its value is asked by someone
    // so if no one ask then it will keep waiting forever and our program won't terminate
    {
        getMessageThree()
    }

    val msg2: Deferred<String> = async {
        getMessageFour() // See this got executed even though no one asked for its value ie.
        // "msg2"
    }

    delay(6000) // maybe doing some Api call
    val getMsg1 = msg1.await() // Observe compiler ran getMessageFour before getMessageThree because coroutine
    // that executes getMessageThree() waited till it's value (ie. msg1) was needed so after
    // 6 seconds (ie. due to above delay()) compiler asked for its value so then
    // coroutine started its execution and returned the answer
    println("$getMsg1")

    println("Reached end of main function ")
}

suspend fun getMessageThree(): String {
    delay(2000L)// pretending to do some work
    println("After working in getMessageThree()")
    return "Hello"
}

suspend fun getMessageFour(): String {
    delay(2000L)// pretending to do some work
    println("After working in getMessageFour()")
    return "World"
}