package _03_functional_but_no_good;

import util.AirportNameUtil;

import java.util.List;
import java.util.function.Function;

public class Sample {
    interface FunctionEx<T, R> {
        R apply(T input) throws Exception;
    }

    //DON't DO THIS
    static <T, R> Function<T, R> convertToRuntimeException(FunctionEx<T, R> func) {
        return input -> {
            try {
                return func.apply(input);
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static void main(String[] args) {
        List<String> iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        iataCodes.stream()
            .map(convertToRuntimeException(AirportNameUtil::getNameOfAirport))
            .forEach(System.out::println);
    }
}
