package _04_functional_deal_with_it;

import java.util.concurrent.Callable;
import java.util.function.Function;

public sealed interface Try<T> permits Success, Failure {
    T getResult();
    Throwable getError();

    static <T> Try<T> of(Callable<T> code) {
        try {
            return new Success<>(code.call());
        } catch(Throwable throwable) {
            return new Failure<>(throwable);
        }
    }

    default <R> Try<R> map(Function<T, R> mapper) {
        return switch (this) {
            case Success<T> success -> Try.of(() -> mapper.apply(success.getResult()));
            case Failure<T> failure -> new Failure<>(failure.getError());
        };
    }
}
