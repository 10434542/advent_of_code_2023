
fun String.getNumberFromString(): Int {
    if (this.isEmpty()) {
        return 0
    }
    val numberList = mutableListOf<String>()
    for (index in this.indices) {
        if (this[index].isDigit()) {
            numberList.add(this[index].toString())
        }
    }
    return if (numberList.size == 1) {
        (numberList[0] + numberList[0]).toInt()
    }
    else {
        (numberList[0] + numberList[numberList.size-1]).toInt()
    }
}

val numberMap = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
)

fun String.getAllNumbersFromString(): Int {
    val result = mutableListOf<String>()
    this.forEachIndexed { index, c ->
        if (c.isDigit()) {
            result.add(c.toString())
        }
        else {
            numberMap.forEach {
                (string, value) ->
                if (this.substring(index).startsWith(string)) {
                    result.add(value)
                }
            }
        }
    }

    return  if (result.isEmpty()) {
        0
    }
    else {
        val toInt = ("${result.first()}${result.last()}").toInt()
        toInt
    }
}

fun dayOne(): Int {
    return "dayOne.txt".asResource()
        .lines()
        .map {
            val result = it.getAllNumbersFromString()
//            println(result)
            result
        }.sumOf { it }
}


