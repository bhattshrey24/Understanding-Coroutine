import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    val job: Job = launch {
        for (i in 0..500) {
            print("$i.")
            delay(50)// because of this delay which is a part of “kotlin.coroutines” package , we
            // were able to cancel the coroutine because now this coroutine is cooperative.
            // If it was some other function like Thread.Sleep(50)
            // then it would not have been able to get canceled
        }

    }

    delay(2000)// we are doing this so that we could see that coroutine got canceled in output
    //    otherwise it would have been canceled right away because "Main" thread coroutine (ie. runBlocking)
    //    would have launched the
    //    coroutine and immediately executed the next line but delay() stop it to execute next line
    //    which is "job.cancelAndJoin()" and hence we are able to see some number being printed before it gets
    //    canceled
    job.cancelAndJoin()

    println()
    println("Reached end of main function ")

}
