import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    val job: Job = launch(Dispatchers.Default) { // While using "isActive" ie. trying to
        // make a coroutine cooperative on our own, we have to
        // use "Dispatchers.Default"
        for (i in 0..500) {
            if (!isActive) { // This is the "CoroutineScope.isActive" flag . Here this will
                // check in each
                // iteration whether the coroutine is still active or not and if it's not then simply
                // break out of loop
                // we can use "break" here too to break this loop but a better way is to use "return@launch"
                return@launch // this means return from coroutine i.e. no other statements after it will be
                    // executed in this coroutine, and it will simply get finished/terminated
            }
            print("$i.")
            Thread.sleep(2)// we have to add this delay because our coroutine was running
            // so quickly that it seemed that it was not getting canceled. So in order to show that
            // it is being canceled we added a delay also observe its Thread.sleep and not delay
        }
    }
    delay(50)
    job.cancelAndJoin()
    println()
    println("Reached end of main function ")

}