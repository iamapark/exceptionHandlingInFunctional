package _04_functional_deal_with_it;

import java.util.List;

import static util.AirportNameUtil.getNameOfAirport;

/*
* https://youtu.be/S5tLVsvbHBg?t=3037
* */
public class Sample {

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        iataCodes.stream()
            .map(iataCode -> Try.of(() -> getNameOfAirport(iataCode)))
            .map(nameTry -> nameTry.map(String::toUpperCase))
            .map(nameTry -> switch(nameTry) {
                case Success<String> success -> success.getResult();
                case Failure<String> err -> "Error: " + err.getError().getMessage();
            })
            .forEach(System.out::println);
    }
}
