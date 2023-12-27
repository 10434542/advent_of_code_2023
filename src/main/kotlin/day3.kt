fun main() {
    val toLines = "dayThree.txt".asResource()
        .lines()
    val indexedLines = toLines.toTypedArray().map { it.toCharArray() }

    var result = 0
    var totalGearRatio = 1
    val gearBorders = mutableListOf<List<IntArray>>()
    val foundNumberIndexes = mutableMapOf<Int, List<List<Int>>>()
    for (i in indexedLines.indices) {
        var currentNumber = ""
        val tempIndexes = mutableListOf<Int>()
        val allIndexes = mutableListOf<List<Int>>()
        for (j in 0..<indexedLines[0].size) {
            if (indexedLines[i][j].isDigit()) {
                currentNumber += indexedLines[i][j]
                tempIndexes.add(j)
            } else if (!indexedLines[i][j].isDigit() && currentNumber.isNotEmpty()) {
                // number ended, check each index found for neighbouring symbols here.
                val numberFound = currentNumber.toInt()
                if (hasNeighbouringSymbol(numberFound, i, tempIndexes, indexedLines)) {
                    result += numberFound
                }
                currentNumber = ""
                allIndexes.add(tempIndexes.toList())
                tempIndexes.clear()
            }
            if (j == indexedLines[0].size - 1 && currentNumber.isNotEmpty()) {
                val numberFound = currentNumber.toInt()
                if (hasNeighbouringSymbol(numberFound, i, tempIndexes, indexedLines)) {
                    result += numberFound
                }
                currentNumber = ""
                allIndexes.add(tempIndexes.toList())
                tempIndexes.clear()
            }

            if (allIndexes.isNotEmpty()) {
                foundNumberIndexes[i] = allIndexes
            }

            if (indexedLines[i][j] == '*') {
                val element = findBorder(i, j, indexedLines)
                gearBorders.add(element)
            }
        }
    }

    gearBorders.forEach {border ->
        var borderingNumbers = 0
        val tempYRangeList = mutableListOf<Int>()
        val tempNumberList = mutableListOf<List<Int>>()
        val yRange = border.map{ yit ->
            yit[0]
        }.distinct().sorted()

        val numbers = yRange.map { y ->
            val numbersPerRow = foundNumberIndexes[y] ?: listOf()
            val whatever = numbersPerRow.map { numberPerRow ->
                numberPerRow.map { listOf(y, it) }
            }
            whatever
        }
        numbers.forEach { row ->
            row.forEach { numberPairs ->
                if (isNeighbour(numberPairs, border)) {
                    borderingNumbers += 1
                    tempNumberList.add(numberPairs.map { it[it.size-1] })
                    tempYRangeList.add(numberPairs[0][0])
                }
            }
        }

        if (borderingNumbers == 2) {
            val firstNumber = tempNumberList[0].map { value ->
                indexedLines[tempYRangeList[0]][value]
            }.joinToString("").toInt()
            val secondNumber = tempNumberList[1].map { value ->
                indexedLines[tempYRangeList[1]][value]
            }.joinToString("").toInt()
            totalGearRatio += (firstNumber*secondNumber)
        }
    }

    println("result for day three part one is $result")
    println("result for day three part two is ${totalGearRatio-1}")

}
fun isNeighbour(numberPairList: List<List<Int>>, borderList: List<IntArray>): Boolean {
    val tempList = borderList.map { it.toList() }
    for (pair in numberPairList) {
        if (tempList.contains(pair)) {
            return true
        }
    }
    return false
}
fun hasNeighbouringSymbol(number: Int, rowNumber: Int, indexList: List<Int>, matrix: List<CharArray>): Boolean {
    // above and below
    var sum = 0
    for (i1 in indexList) {
        if ((rowNumber + 1 < matrix.size) && isSymbolNotPoint(matrix[rowNumber + 1][i1])) {
            return true
        }
        if ((rowNumber - 1 >= 0) && isSymbolNotPoint(matrix[rowNumber - 1][i1])) {
            return true
        }
    }
    // right
    if ((indexList.last + 1) < matrix[0].size && isSymbolNotPoint(matrix[rowNumber][indexList.last + 1])) {
        return true
    }
    // left
    if ((indexList.first - 1) >= 0 && isSymbolNotPoint(matrix[rowNumber][indexList.first - 1])) {
        return true
    }

    // bottom right
    if ((rowNumber + 1) < matrix.size && (indexList.last + 1) < matrix[0].size && isSymbolNotPoint(matrix[rowNumber + 1][indexList.last + 1])) {
        return true
    }
    // top right
    if ((rowNumber - 1) >= 0 && (indexList.last + 1) < matrix[0].size && isSymbolNotPoint(matrix[rowNumber - 1][indexList.last + 1])) {
        return true
    }

    // bottom left
    if ((rowNumber + 1) < matrix.size && (indexList.first - 1) >= 0 && isSymbolNotPoint(matrix[rowNumber + 1][indexList.first - 1])) {
        return true
    }
    // top left
    if ((rowNumber - 1) >= 0 && (indexList.first - 1) >= 0 && isSymbolNotPoint(matrix[rowNumber - 1][indexList.first - 1])) {
        return true
    }

    return false
}

fun findBorder(rowNumber: Int, index: Int, matrix: List<CharArray>): List<IntArray> {
    val border = mutableListOf<IntArray>()
    if ((rowNumber + 1 < matrix.size)) {
        border.add(intArrayOf(rowNumber + 1, index))
    }
    if ((rowNumber - 1 >= 0)) {
        border.add(intArrayOf(rowNumber - 1, index))
    }

    // right
    if ((index + 1) < matrix[0].size) {
        border.add(intArrayOf(rowNumber, index + 1))
    }
    // left
    if ((index - 1) >= 0) {
        border.add(intArrayOf(rowNumber, index - 1))
    }

    // bottom right
    if ((rowNumber + 1) < matrix.size && (index + 1) < matrix[0].size) {
        border.add(intArrayOf(rowNumber + 1, index + 1))

    }
    // top right
    if ((rowNumber - 1) >= 0 && (index + 1) < matrix[0].size) {
        border.add(intArrayOf(rowNumber - 1, index + 1))
    }

    // bottom left
    if ((rowNumber + 1) < matrix.size && (index - 1) >= 0) {
        border.add(intArrayOf(rowNumber + 1, index - 1))

    }
    // top left
    if ((rowNumber - 1) >= 0 && (index - 1) >= 0) {
        border.add(intArrayOf(rowNumber - 1, index - 1))

    }
    return border
}

fun isSymbolNotPoint(char: Char): Boolean {
    return (!char.isDigit() && char != '.')
}