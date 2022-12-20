package _04_functional_deal_with_it;

import java.util.List;

import static util.AirportNameUtil.getNameOfAirport;

/*
* Video related explanation of this code => https://youtu.be/S5tLVsvbHBg?t=3037
*
* - Concept of this code: Treat error as data and deal with it downstream and keep going forward
* - This code will work... but disadvantage of this code: The code is no longer cohesive
*   : In every step, we should ask if it's error or not
*   : The functional pipeline is no longer as clean as it was before
*   : The simplicity, clarity, the ease are much lower with this than when we deal with pure functions
*     that do not throw any exception
* - How do we deal with multiple levels of exception?
*   : Imperative programming can be much better suited to handling multi levels of exception
*     than functional programming. Don't force to yourselves to stick to it to say I've got to write
*     this code in functional style. if the code is easier to understand, if the code is easier to maintain,
*     I would say go with it. we're not in a competition (Imperative vs Functional)
*   : Be mindful of why exceptions are not really handled by the stream API
*     It may not be the right way to handle exceptions by adding a solution while using the stream API
*     This is because the design of the stream API itself did not keep exception handling in mind
* */
public class Sample {

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        iataCodes.stream()
            .map(Sample::tryToGetNameOfAirport)
            .map(nameTry -> nameTry.map(String::toUpperCase))
            .map(nameTry -> switch(nameTry) {
                case Success<String> success -> success.getResult();
                case Failure<String> err -> "Error: " + err.getError().getMessage();
            })
            .forEach(System.out::println);
    }

    private static Try<String> tryToGetNameOfAirport(String iataCode) {
        return Try.of(() -> getNameOfAirport(iataCode));
    }
}
