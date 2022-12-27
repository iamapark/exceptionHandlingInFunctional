package _04_functional_deal_with_it;

import io.vavr.control.Either;

import java.util.List;

import static util.AirportNameUtil.getNameOfAirport;

/*
   ※ This is not the code included in the source video of this repo (https://www.youtube.com/watch?v=S5tLVsvbHBg) ※

   Either (https://docs.vavr.io/#_either)
  - A type that can have one of two values
  - Same concept as Scala(Either)
  - Either is not a Monad, while Try is Monadic, so Either is replaced by Try
*/
public class Sample03 {

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        iataCodes
            .stream()
            .map(Sample03::tryToGetNameOfAirportEither)
            .map(nameEither -> nameEither.fold(e -> "Error: " + e.getMessage(), String::toUpperCase))
            .forEach(System.out::println);
    }

    private static Either<Exception, String> tryToGetNameOfAirportEither(String iataCode) {
        try {
            return Either.right(getNameOfAirport(iataCode));
        } catch (Exception e) {
            return Either.left(e);
        }
    }
}
