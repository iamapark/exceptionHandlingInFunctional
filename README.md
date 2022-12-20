# exceptionHandlingInFunctional

This source code was from this video (https://www.youtube.com/watch?v=S5tLVsvbHBg)

## Conclusion
``
None of the solutions here is going to be perfect unfortunately
``


### If we don't have to deal with an exception
=> Go with Stream

### If we don't have to deal with an exception and only care of single data asynchronously
=> Go with CompletableFuture

### If we have to deal with an exception and care of multiple data
=> Go with Reactive stream

### If we have to deal with an exception and care of multiple data and need to data processing after exception handling
=> Go with Imperative programming
