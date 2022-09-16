import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val job = launch(newSingleThreadContext("My New Thread")){
        // "newSingleThreadContext" will start the coroutine in a brand-new thread, and it takes
        // the name that we want to give to the new thread as parameter
        delay(1000)
        println("Running on  ${Thread.currentThread().name}")
    }

}