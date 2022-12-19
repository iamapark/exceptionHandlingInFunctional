package util;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class AirportNameUtil {
    public static String getNameOfAirport(String iata) throws IOException {
        var url = "https://soa.smext.faa.gov/asws/api/airport/status/" + iata;

        try(var scanner = new Scanner(new URL(url).openStream())) {
            var response = scanner.nextLine();

            if(!response.contains("Name")) {
                throw new RuntimeException("Invalid airport code " + iata);
            }

            return response.split("\"")[3];
            //way too lazy to do the real work to get the data
        }
    }
}
