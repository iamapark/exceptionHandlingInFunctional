package _01_imperative_style;

import java.util.List;

import static util.AirportNameUtil.getNameOfAirport;

public class Sample {

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        for(var iataCode: iataCodes) {
            try {
                System.out.println(getNameOfAirport(iataCode));
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
