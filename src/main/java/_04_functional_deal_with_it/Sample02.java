package _04_functional_deal_with_it;

import io.vavr.control.Try;

import java.util.List;

import static util.AirportNameUtil.getNameOfAirport;

/*
* Exact same code with Sample01, but use Java library vavr
* */
public class Sample02 {

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        iataCodes
            .stream()
            .map(Sample02::tryToGetNameOfAirport)
            .map(nameTry -> nameTry.map(String::toUpperCase))
            .map(nameTry -> {
                if (nameTry.isSuccess()) {
                    return nameTry.get();
                } else {
                   return  "Error: " + nameTry.getCause().getMessage();
                }
            }).forEach(System.out::println);
    }

    private static Try<String> tryToGetNameOfAirport(String iataCode) {
        return Try.of(() -> getNameOfAirport(iataCode));
    }
}
