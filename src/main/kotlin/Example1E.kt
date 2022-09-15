import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Main program starts on thread : ${Thread.currentThread().name}")

    GlobalScope.launch {// Suppose this new coroutine is executed on Thread "t1"

        println(" ${Thread.currentThread().name} starting to work") // this line will also execute on
        // "t1" by this coroutine

        delay(4000) // now this will also get executed but now coroutine will
        // stop its execution for 4 seconds and now "t1" is free to work with any other coroutines

        println(" ${Thread.currentThread().name} finished working")// now after 4 seconds it's not
        // necessary that this statement will be executed by "t1" only because it is possible that in
        // mean time t1 has reached its capacity and now can't take any new coroutine so
        // instead this coroutine will be
        // executed by any available thread at that time . This will happen in case of all suspend
        // functions and not just 'delay()'
    }

    runBlocking { // Creates a new coroutine which runs on the main thread

        myDelayFunction(5000)// Since we cannot call suspend function (i.e. in our
            // case myDelayFunction() ) outside a
        // coroutine therefore we used this "runBlocking" coroutine which is a special coroutine that
        // runs on main thread and hence can block the execution
        // of main thread . This is still not the
        // right way to make thread wait for coroutine to finish its work
    }
    println("Reached end of main function")

}
suspend fun myDelayFunction(time: Long) { // simply showing how we can create our own suspend functions
    //  are like normal function which means they can have arguments and return type
    // the only difference is that their execution can be stopped in between and can be resumed anytime.
    delay(time)

}
