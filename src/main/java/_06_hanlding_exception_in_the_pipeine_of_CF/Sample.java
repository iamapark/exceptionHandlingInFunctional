package _06_hanlding_exception_in_the_pipeine_of_CF;

import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

//A bit better with a good separation of concern for each function
//then... focuses on data
//exceptionally focuses on error
//
//But, we still have to deal with a mixture of data handling and error
//handling in different stages of the pipeline.
public class Sample {

    public static String getNameOfAirportWithoutThrows(String iata) {
        try {
            var url = "https://soa.smext.faa.gov/asws/api/airport/status/" + iata;

            try(var scanner = new Scanner(new URL(url).openStream())) {
                var response = scanner.nextLine();

                if(!response.contains("Name")) {
                    throw new RuntimeException("Invalid airport code " + iata);
                }

                return response.split("\"")[3];
                //way too lazy to do the real work to get the data
            }
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
