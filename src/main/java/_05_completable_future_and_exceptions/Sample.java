package _05_completable_future_and_exceptions;

import java.util.concurrent.CompletableFuture;

/*
Promises or CompletableFuture

States:
-pending
-resolved (terminal)
-rejected (terminal)

   =======X=========X=============================X=====
d -> f -> P -> f -> P -> f -> P -> f -> P -> f -> P
   ===========================E=========E===============

X - data
E - error

Stream may have 0, 1, or any number of data

CompletableFuture may have 0 or 1 piece of data

CompletableFuture  Function  next?

CompletableFuture  Function  next?
  resolved            ✓      go to the next then
  resolved            x      go to the next exceptionally
  rejected            ✓      go to the next then
  rejected            x      go to the next exceptionally
*/
public class Sample {

    public static int compute() {
        return 2;
    }

    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(Sample::compute);
    }

    public static int transform(int number, int multiplier) {
        System.out.println("transform called...");

        if(Math.random() > 0.5) {
            System.out.println("transform failed...");
            throw new RuntimeException("something went wrong");
        }

        return number * multiplier;
    }

    public static void main(String[] args) {
        create()
            .thenApply(data -> transform(data, 10))
            .exceptionally(Sample::handleException)
            .thenApply(data -> transform(data, 2))
            .exceptionally(Sample::handleException)
            .thenAccept(System.out::println);
    }

    public static int handleException(Throwable throwable) {
        System.out.println("handling exception... " + throwable.getMessage());
        return 100;
    }
}
