import kotlin.concurrent.thread

fun main() {
    // wait 4-5 seconds for full output since
    // here I'm making the threads sleep
    println("Main program starts on thread : ${Thread.currentThread().name}")

    thread { // background thread 1 (Doing Api call)
        println(" ${Thread.currentThread().name} starting to work")
        Thread.sleep(4000)
        println(" ${Thread.currentThread().name} finished doing API Call!!")
    }
    thread { // background thread 2 (Doing heavy calculations)
        println(" ${Thread.currentThread().name} starting to work")
        Thread.sleep(4000)
        println(" ${Thread.currentThread().name} finished doing heavy calculations!!")
    }
    thread { // background thread 2 (Doing db operations)
        println(" ${Thread.currentThread().name} starting to work")
        Thread.sleep(4000)
        println(" ${Thread.currentThread().name} finished doing db operations!!")
    }

    println("Reached end of main function")

    // Observe what happens actually is that the main thread will create these threads
    // and start them ie. first it will execute println("..") then it will reach 1st thread lambda
    // and create 1st thread and start it then create reach 2nd thread lambda statement and create 2nd
    // thread and start it and then create 3rd thread lambda statement and start it and then simply execute
    // last println("...") . It won't stop for any thread to finish its execution

    // Observe they all finished there work together ie. after 4 seconds which means they were working
    // parallel
}