import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts on thread : ${Thread.currentThread().name}")

    var jobDeferred: Deferred<String> = async {
        // Observe the difference between async and launch , async doesn't return "Job" instead
        // it returns "Deferred" which is child interface of "Job"
        // Since we are returning "result" of dummy api which is of type "String"
        // therefore type of Deferred is "String"
        // This will also run on same thread as
        // parent(ie. runBlocking) and with same coroutine scope
        println(" ${Thread.currentThread().name} starting to work")
        val result = apiResponseDummy(4000)
        println(" ${Thread.currentThread().name} finished working")

        result // this will be returned by this lambda function
    }

    // jobDeferred.join() // since Deferred is child interface of Job so this statement
    // will work but you won't be able to retrieve the
    // value returned by async using this therefore use .await() instead as shown below

    val res = jobDeferred.await()

    println("Reached end of main function and result from api is : $res")

}

suspend fun apiResponseDummy(time: Long): String {
    delay(time)
    return "Data xyz from api"
}