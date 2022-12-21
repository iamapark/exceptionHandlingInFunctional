package _08_functional_deal_with_it_kotlin

import io.vavr.control.Try
import util.AirportNameUtil.getNameOfAirport

fun main() {
    val iataCodes = listOf("AUS", "IAH", "DFW", "TAS", "SAT")

    // If an exception occurs, an error message is displayed along with "Error: "
    iataCodes
        .map { Try.of { getNameOfAirport(it) } }
        .map { it.map { airportName -> airportName.uppercase() } }
        .map { tryName -> tryName.recover { ex -> "Error: ${ex.message}" }.get() }
        .forEach { println(it) }

    println("===============================")

    // If an exception occurs, ignore the iataCode
    iataCodes
        .map { Try.of { getNameOfAirport(it) } }
        .map { it.map { airportName -> airportName.uppercase() } }
        .filter { it.isSuccess }
        .map { it.get() }
        .forEach { println(it) }
}
