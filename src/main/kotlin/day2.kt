fun String.parseGame(): List<Game> {
    val games = this.asResource()
        .lines()
        .filter { it.isNotEmpty() }
        .map { line ->
            val gameAndRounds = line.split(":")
            val gameNumber = gameAndRounds.first.split(" ").last?.toInt()!!
            val rounds = gameAndRounds.last.split(";")
                .map {
                    var blue = 0
                    var red = 0
                    var green = 0
                    val roundElements = it.split(",")
                    roundElements.forEach { roundElement ->
                        if (roundElement.contains("blue")) {
                            // not first but entire number before it
                            blue = roundElement.trim().split(" ")[0].trim().toInt()
                        } else if (roundElement.contains("red")) {
                            red = roundElement.trim().split(" ")[0].trim().toInt()
                        } else {
                            green = roundElement.trim().split(" ")[0].trim().toInt()
                        }
                    }
                    Round(blue, red, green)
                }
            Game(gameNumber, rounds)
        }.toList()
    return games
}

class Round(var blues: Int, var reds: Int, var greens: Int) {}

class Game(var number: Int, var rounds: List<Round>) {}

fun Game.isPossible(triple: Triple<Int, Int, Int>): Boolean {
    for (round in rounds) {
        if (round.reds > triple.first || round.greens > triple.second || round.blues > triple.third) {
            return false
        }
    }
    return true
}

fun Game.findPower(): Int {
    // order: reds, greens, and blues
    var maxReds = 0
    var maxGreens = 0
    var maxBlues = 0
    for (round in rounds) {
        if (round.reds > maxReds) {
            maxReds = round.reds
        }
        if (round.greens > maxGreens) {
            maxGreens = round.greens
        }
        if (round.blues > maxBlues) {
            maxBlues = round.blues
        }
    }
    return maxReds * maxGreens * maxBlues
}

fun main() {
    var resultPartOne = 0
    var resultPartTwo = 0
    "dayTwo.txt".parseGame().forEach {

        if (it.isPossible(Triple(12, 13, 14))) {
            resultPartOne += it.number
        }
        resultPartTwo += it.findPower()
    }
    println("result part one is $resultPartOne")
    println("result part two is $resultPartTwo")
}