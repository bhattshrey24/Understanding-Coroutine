import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")
    // What is cooperative coroutine is explained with example in Example2D
    var job: Job = launch {// The code here has to cooperative in order to get canceled
        println(" ${Thread.currentThread().name} starting to work")
        println(" ${Thread.currentThread().name} finished working")

    }
    job.cancel() // If coroutine is cooperative then it will get canceled but if it's not then it won't get canceled
    job.join()// This will only be executed when above statement fails to cancel the coroutine.These
    // 2 functions are used so often that there is a special function that combines these
    // 2 and that function is "job.cancelAndJoin()" which simply states that if coroutine is cooperative
    // then cancel it else simply wait for it to complete.

    println("Reached end of main function ")

}
