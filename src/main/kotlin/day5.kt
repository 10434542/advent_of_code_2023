fun main() {
    val bla = "dayFive.txt".asResource().split("\n").mapNotNull { line -> line.trimIndent().takeIf { it.isNotEmpty() } }
    println("result is  ${test(bla)}")
}

fun test(input: List<String>): Long = with(input.iterator()) {
    var value = next().removePrefix("seeds:").split(" ").mapNotNull { it.toLongOrNull() }.min()
    for (line in this) {
        if (line.isBlank() || line.first().isLetter()) continue
        val (dstStart, srcStart, length) = line.split(" ").map { it.toLong() }
        if (value in srcStart..<srcStart + length) {
            println("value in range is $value")
            value = dstStart + value - srcStart
            while (hasNext()) {
                val nextLine = next()
                if (nextLine.isNotBlank() && nextLine.first().isLetter()) break
            }
        }
    }
    return value
}

