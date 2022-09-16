import kotlin.concurrent.thread

// These examples are referred from this video :https://www.youtube.com/watch?v=lmRzRKIsn1g
fun main() {
    println("Main program starts on thread : ${Thread.currentThread().name}")

    thread { // This is how we can create thread in kotlin , This is worker/background thread
        println(" ${Thread.currentThread().name} starting to work")
        Thread.sleep(4000)// Mimicking a API call or doing some heavy calculations
        println(" ${Thread.currentThread().name} finished working")
    }

    println("Reached end of main function")// we will reach here before Background thread
    // finishes its work because this is being executed by main thread
    // which is independent of background thread so technically our application will end when background
    // thread is done with it's work and not when we reach the end of main()
    // application waits for all our background threads to finish their work and then
    // only end the application
}

