import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SimpleTest {
    @Test
    fun myFirstTest() = runBlocking {
        val result = apiResponseDummy(4000) // this is the suspend function
        // that I created in "Example2B" class . Because of runBlocking we were able to call
        // suspend function here. Now we did not create new launch coroutine here because
        // it is rule of a test case that no test case should not run parallel to other test cases
        // therefore now runBlocking will stop further test cases from being executed until
        // this "myDelayFunction" is finished with its work.
        println("Response received!!")
        Assert.assertEquals("Data xyz from api", result)
    }

}