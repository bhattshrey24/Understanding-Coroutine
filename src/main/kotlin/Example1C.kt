import kotlinx.coroutines.*


fun main() {
    println("Main program starts on thread : ${Thread.currentThread().name}")

    GlobalScope.launch {
        println(" ${Thread.currentThread().name} starting to work")
        Thread.sleep(4000) // Observe its "Thread.sleep" and not delay() ,
        // delay() will simply
        // delay the coroutine but Thread.sleep will make the Thread to sleep on which this
        // coroutine is working and will also result in all other coroutines
        // which were working on this thread to sleep too.
        println(" ${Thread.currentThread().name} finished working")
    }

    //  NO OUTPUT RELATED TO BACKGROUND THREAD  When a coroutine is launched then the
    //  application by default do not know
    // that it has to wait for the coroutine to end its work unlike the case in thread.
    // Now if you want your application to wait ie. Main thread to wait for coroutine
    // then you have to do it manually by telling main thread to stop till coroutine is done
    // doing its work . See Example1D

    Thread.sleep(1000)
    println("Reached end of main function")
}
