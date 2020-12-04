import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day02Test{

    @Test
    fun isValidPassword_test(){
        assertTrue(Day02().isValid("1-3 a: abcde\n"))
        assertFalse(Day02().isValid("1-3 b: cdefg\n"))
        assertTrue(Day02().isValid("2-9 c: ccccccccc"))

    }
}