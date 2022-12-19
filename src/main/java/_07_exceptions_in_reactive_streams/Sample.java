package _07_exceptions_in_reactive_streams;

import io.reactivex.rxjava3.core.Flowable;
import util.AirportNameUtil;

import java.util.List;

/*
Stream                        CompletableFuture      Reactive Stream
0, 1, or more pieces of data  0 or 1                 0, 1, or more

Stream                       Reactive Stream
pipeline of functions        pipeline of functions
lazy evaluation              lazy evaluation

How do handle exceptions?
Good luck                    Treat error as data

One channel of data          3 channels
                             -----> data channel
                             -----> error channel
                             -----> complete channel

			     data flows through the data channel
			     if no more data, a signal may flow through
			       the complete channel and the data channel
			       is closed.

                             if there is an error, an error flows through
			       the error channel and the data channel
			       is closed.


map, filter, etc. can't call           The map, filter, etc. can have lambdas that may throw checked exception
lambdas that have checked exception
*/
public class Sample {

    public static void main(String[] args) {
        var iataCodes = List.of("AUS", "IAH", "DFW", "TAS", "SAT");

        Flowable.fromIterable(iataCodes)
            .map(AirportNameUtil::getNameOfAirport)
            .map(String::toUpperCase)
            .subscribe(
                System.out::println, //Data channel
                error -> System.out.println("ERROR: " + error), //error channel
                () -> System.out.println("DONE")
            );
    }
}
