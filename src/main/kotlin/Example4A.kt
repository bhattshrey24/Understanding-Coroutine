import kotlinx.coroutines.*

fun main() = runBlocking {
    // CS means CoroutineScope

    println("runBlocking CS : $this") // observe the output carefully
    // it shows "BlockingCoroutine" which tells us that this is being run by runBlocking coroutine
    // then it shows {Active} which tells that when this statement was executed then the coroutine
    // was active and in the end it shows hex code which is unique for each coroutine

    val job = launch {
        println("launch CS : $this")
        launch {
            println("child launch CS : $this") // Observe its hex code is different
            // from its parents hex code which tells us that each coroutine irrespective of their parent
            // has their own CoroutineScope
        }
    }
    val jobD = async {
        println("async CS : $this")
    }


}