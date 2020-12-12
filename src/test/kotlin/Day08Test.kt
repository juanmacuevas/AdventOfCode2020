import jdk.nashorn.internal.objects.NativeRegExp.test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class Day08Test {

    @Test
    fun partOne() {
        assertTrue(Day08().partOne()==1709)
    }

    @Test
    fun partTwo() {
        assertTrue(Day08().partTwo()==1976)
    }
}