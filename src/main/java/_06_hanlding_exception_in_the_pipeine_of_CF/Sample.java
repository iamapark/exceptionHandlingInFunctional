package _06_hanlding_exception_in_the_pipeine_of_CF;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static util.AirportNameUtil.getNameOfAirport;

// Concept of this code: mixture of CompletableFuture and Stream API

//A bit better with a good separation of concern for each function
//then... focuses on data
//exceptionally focuses on error
//
//But, we still have to deal with a mixture of data handling and error
//handling in different stages of the pipeline.
public class Sample {

    public static String getNameOfAirportWithoutThrows(String iata) {
        try {
            return getNameOfAirport(iata);
        }catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static CompletableFuture<Void> processIATA(String iataCode) {
        return CompletableFuture.supplyAsync(() -> getNameOfAirportWithoutThrows(iataCode))
            .thenApply(String::toUpperCase)
            .exceptionally(Throwable::getMessage)
            .thenAccept(System.out::println);
    }

    public static void main(String[] args) {
        var iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        var tasks = iataCodes.stream()
                .map(Sample::processIATA)
                .toList();

        try { Thread.sleep(10000); } catch(Exception ex) {}
    }
}
