package _02_functional_but_no_good;

import java.util.List;

import static util.AirportNameUtil.getNameOfAirport;

public class Sample {
    public static String getNameOfAirport2(String iata) {
        try {
            return getNameOfAirport(iata);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        iataCodes.stream()
            .map(Sample::getNameOfAirport2)
            .forEach(System.out::println);
        //curl up in a corner and cry

        //don't do this
    }
}
