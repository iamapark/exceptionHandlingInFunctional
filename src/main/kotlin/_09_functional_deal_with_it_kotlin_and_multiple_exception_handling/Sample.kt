package _09_functional_deal_with_it_kotlin_and_multiple_exception_handling

import io.vavr.control.Try
import java.io.IOException

fun main() {
    val iataCodes = listOf("AUS", "IAH", "DFW", "TAS", "SAT")

    // There will be 3 transformation
    // first transform: uppercase but may be exception
        // fallback logic of first: ignore if there is an exception
    // second transform: lowercase but may be exception
        // fallback logic of second: print "error: {error message}" and ignore it if there is an exception
    // third transform: reversed
        // fallback logic of third: x
    // final: print the result

    iataCodes.asSequence()

        // first transform
        .map { Try.of { firstTransformMayThrowException(it) } }
        // first fallback logic
        .filter { it.isSuccess }

        // second transform
        .map { it.map { result1 -> secondTransformMayThrowException(result1) } }
        // second fallback logic
        .map { it.recover { ex -> println("error: ${ex.message}"); null } }.filter { it.get() != null }

        // third transform
        .map { it.map { result2 -> thirdTransformPureFunction(result2) } }

        // final
        .forEach {
            if (it.get().length == 3) {
                println("Final result: ${it.get()}")
            } else {
                throw AssertionError("it should be 3 characters: $it")
            }
        }
}

@Throws(IOException::class)
private fun firstTransformMayThrowException(code: String): String {
    if (Math.random() > 0.5) {
        println("[transformMayThrowException1] code: $code")
        throw IOException("[transformMayThrowException1] IOException")
    }

    return code.uppercase()
}
private fun secondTransformMayThrowException(code: String): String {
    if (Math.random() > 0.5) {
        // println("[transformMayThrowException2] code: $code")
        throw ArithmeticException("[transformMayThrowException2] ArithmeticException: $code")
    }

    return code.lowercase()
}

private fun thirdTransformPureFunction(code: String): String {
    return code.reversed()
}
