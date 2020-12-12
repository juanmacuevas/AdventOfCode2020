import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class Day07Test {

    @Test
    fun `calculate number of bags that could contain a shiny gold bag`() {
        assertTrue(Day07().partOne() == 139)
    }

    @Test
    fun `calculate number of bags required inside a shiny gold bag`() {
        assertTrue(Day07().partTwo() == 58175)
    }

    @Test
    fun `parse rule no other bags correctly`() {
        val rule: Pair<String, List<String>> = Day07().ruleToMap("bright lavender bags contain no other bags.")
        assertTrue(rule.first == "bright lavender")
        assertTrue(rule.second.isEmpty())
        assertEquals(("bright lavender" to emptyList<String>()), rule)
    }

    @Test
    fun `parse rule contains other bags correctly`() {
        val a: Pair<String, List<String>> =
            Day07().ruleToMap("clear yellow bags contain 5 mirrored green bags, 4 striped red bags, 3 plaid magenta bags, 1 bright lavender bag.")
        assertTrue(a.first == "clear yellow")
        assertTrue(a.second.containsAll(listOf("mirrored green", "striped red", "plaid magenta", "bright lavender")))

    }

}