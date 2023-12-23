fun main() {
    val window = ArrayDeque<CharArray>()
    "dayThree.txt".asResource()
        .lines()
        .forEach {
            // only check whether there are adjacent numbers when you find a symbel,
            // keep previous and next line in buffer or something (so take three at a time using
            // for instance double ended queue?
            // when you find symbol, get its index and check whether
            val latestLine = it.toCharArray()
            window.add(late stLine)
            if (window.size > 3) {
                window.remove(window.first)
            }
            // findSymbols if any.
            val symbolIndicesList = mutableListOf<Int>()
            latestLine.forEachIndexed { index, c ->
                if (!c.isLetterOrDigit() && c != '.') {
                    symbolIndicesList.add(index)
                }
            }

        }
}