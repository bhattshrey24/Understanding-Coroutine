import kotlinx.coroutines.*

fun main() = runBlocking { // this is nothing new , we know runBlocking is a
    // function, and it returns "Unit" and not a "Job". We did this so that we can use delay
    // and launch inside it. Remember runBlocking runs on main thread

    println("Main program starts on thread : ${Thread.currentThread().name}")

    var job: Job = launch {// launch returns a "Job" object
        println(" ${Thread.currentThread().name} starting to work")
        delay(4000)
        println(" ${Thread.currentThread().name} finished working")
    }

    //delay(5000) // instead of delay we used join() which is how we should make thread or coroutine to
    // wait for another coroutine to finish
    job.join()// this will stop the coroutine(i.e. coroutine started by runBlocking{} )
    // till the coroutine (which we launched using "launch" ) finishes its work

    println("Reached end of main function") // now see this will be executed after the coroutine (which
     // we launched using "launch" ) is done with its work
}
