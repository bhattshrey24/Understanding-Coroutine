import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    // "coroutineContext" we can access coroutines context using this property

    // Without Parameter (AKA "Confined Dispatcher")
    val job = launch { // See this "launch" has no parameter so that means the coroutine
        // that it will start will have same coroutineContext(ie. the Dispatcher) as its parents
        // i.e. runBlocking's coroutineContext which means that it will be executed on "Main" thread
        println("C1: ${Thread.currentThread().name}")// See this runs on "Main" thread
        delay(1000)
        println("C1 after delay: ${Thread.currentThread().name}") // Here even after delay this will be executed
        // by same thread which executed it before i.e. in this case "Main" thread, which is not the case
        // in Dispatchers.Default, Dispatchers.Unconfined or GlobalScope.launch
    }

    // With Parameter
    val job2 = launch(Dispatchers.Default) { // Dispatchers.Default properties are EXACTLY same
        // as GlobalScope.launch which means it will create coroutine at application level which will stay as long
        // as application which means it will be executed on a separate background thread
        println("C2: ${Thread.currentThread().name}")
        delay(1000)
        println("C2 after delay: ${Thread.currentThread().name}") // here it may or may not be executed by same
        // thread which executed this coroutine before delay() (Just like in "GlobalScope.launch")
    }

    val job3 = launch(Dispatchers.Unconfined) { // in case of "Dispatchers.Unconfined" , the coroutine
        // that it will launch will inherit coroutineContext from immediate parent
        println("C3: ${Thread.currentThread().name}") // will be executed in "Main" thread because its parent is runBlocking
        delay(1000)
        println("C3 after delay: ${Thread.currentThread().name}") // will be executed in some
        // other background thread i.e. other than "Main" ie. other than the one in which it was executed
        // before
    }

    //Explicitly passing parents coroutineContext to child using "coroutineContext" which is context of parent ie.
    // runBlocking's context
    val job4 = launch(coroutineContext) { // It is like confined dispatcher ie. even after delay()
        // the thread on which this coroutine runs will not change
        println("C4: ${Thread.currentThread().name}")
        delay(1000)
        println("C4 after delay: ${Thread.currentThread().name}")
    }

    // Dispatchers.Main and Io are related to "Android". Main is used to do UI changes in android and
    // IO is used to perform Input/Output operations

}
