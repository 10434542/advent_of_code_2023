fun main() {
    // split on the newline characters..
    val bla = "dayFive.txt".asResource().split("\n").mapNotNull { line -> line.trimIndent().takeIf { it.isNotEmpty() } }
    val seedList = bla[0].split(":")[1].trim().split(" ").map{ it.toLong()}
    val result = seedList.map { seed ->
        test(bla, seed)
    }
    result.forEach { println(it) }
}

fun test(input: List<String>, whatever: Long): Long = with(input.iterator()) {
    var value = whatever
    for (line in this) {
        println(line)
        if (line.first().isLetter()) continue
        val (destinationStart, sourceStart, length) = line.split(" ").map { it.toLong() }
        if (value in sourceStart..<sourceStart + length) {
            value = destinationStart + value - sourceStart
            // find next part of mapping
            while (hasNext()) {
                val nextLine = next()
                if (nextLine.isNotBlank() && nextLine.first().isLetter()) break
            }
        }
    }
    return value
}

