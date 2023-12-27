import kotlin.math.pow

fun main() {
    var resultPartOne = 0
    "dayFour.txt".asResource()
        .lines()
        .filter { it.isNotEmpty() }
        .forEach { line ->
            val cardAndHands = line.split(":")
            val cards = cardAndHands[1].split("|")
            val firstCard = cards[0].trim().split(" ").filter { it.isNotEmpty() }
            val secondCard = cards[1].trim().split(" ").filter { it.isNotEmpty() }
            val firstSet = firstCard.toSet()
            val secondSet = secondCard.toSet()
            val amountOfMatches = firstSet.intersect(secondSet).size.toDouble()
            val tempResult = 2.0.pow(amountOfMatches - 1)
            resultPartOne += tempResult.toInt()
        }
    println("result for day four part one is $resultPartOne")
}