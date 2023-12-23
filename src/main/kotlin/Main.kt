fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("answer of the first day part two is ${dayOne()}")
//    println("answer of the first day part one is ${dayTwo()}")
}

fun String.asResource(): String {
    return this::javaClass::class.java.getResource(this).readText()
}