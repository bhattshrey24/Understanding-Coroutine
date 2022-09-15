import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    println("Main program starts on thread : ${Thread.currentThread().name}")

    GlobalScope.launch {
        println(" ${Thread.currentThread().name} starting to work")
        Thread.sleep(4000)
        println(" ${Thread.currentThread().name} finished working")
    }

    Thread.sleep(5000) // See I made main thread to sleep more than the time required for
    // coroutine to finish its work because otherwise main thread will stop before coroutine is
    // done with its execution. Basically when we pause the main thread then CPU tries not to stay
    // ideal, so it starts the coroutine but if it got to know that main thread is done with
    // its sleep then it doesn't care about coroutine i.e. whether it's done working or not and will
    // simply finish the application . This is not the right way to make this work
    // because we don't know beforehand how much time our coroutine will take to finish the given work
    // but right now we are just trying to understand the concept. In later examples I
    //  have shown how to make "main" wait correctly
    // .Try making "main" sleep just for 2000 and see what happens.
    println("Reached end of main function")
}
