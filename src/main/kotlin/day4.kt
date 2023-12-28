import kotlin.math.pow

fun main() {
    var resultPartOne = 0
    val allCards = "dayFour.txt".asResource()
        .lines()
        .filter { it.isNotEmpty() }
    val amountOfCards = allCards.map { 1 }.toMutableList()
    // below is correct
//    val amountOfCards = mutableListOf(1,2,3,4,5,6)
    allCards
        .forEachIndexed { index, line ->
            val cardAndHands = line.split(":")
            val cards = cardAndHands[1].split("|")
            val firstCard = cards[0].trim().split(" ").filter { it.isNotEmpty() }
            val secondCard = cards[1].trim().split(" ").filter { it.isNotEmpty() }
            val firstSet = firstCard.toSet()
            val secondSet = secondCard.toSet()
            val amountOfMatches = firstSet.intersect(secondSet).size.toDouble()
            val tempResult = 2.0.pow(amountOfMatches - 1)
            resultPartOne += tempResult.toInt()
            print("${amountOfMatches.toInt()} ")
            for (currentCard in 1..amountOfCards[index]) {
                print("$currentCard found extra cards for ")
                for(i in 1..amountOfMatches.toInt()) {
                    print("${index + i + 1}")
                    // increase values of amountOfCards here, take into account the size of amountOfCards
                }


            }
            println("")
        }
    print(amountOfCards)
    println("result for day four part one is $resultPartOne")



}