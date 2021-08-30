package com.tsm.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactIveTest {

    @Test
    public void monoReactiveTest(){
        Mono<String> monoString = Mono.just("testing mono of reactive").log();
        monoString.subscribe(System.out::println);

        //onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription) -> request(unbounded)
        // -> onNext(testing mono of reactive) -> onComplete()
    }
    @Test
    public void monoReactive_onErrorTest(){
        Mono<?> monoString = Mono.just("testing mono of reactive")
                .then(Mono.error(new RuntimeException("test erorr messsage")))
                .log();
//        monoString.subscribe(System.out::println);
        monoString.subscribe(System.out::println,(ex)->System.out.println(ex.getMessage()));

        //onSubscribe() -> request(unbounded)
        // -> > onError()
    }

    @Test
    public void fluxTest(){
        Flux<String> flux = Flux.just("string 1", "string 2", "string 3", "string 4").log();
        flux.subscribe(System.out::println);
        //onSubscribe -> request(unbounded) ->
        // onNext(string 1) ->onNext(string 2)->onNext(string 3)->onNext(string 4)
        //onComplete()
    }

    @Test
    public void fluxTestAddingInBetween(){
        Flux<String> flux = Flux.just("string 1", "string 2", "string 3", "string 4")
                .concatWithValues("string 5")
                .log();
        flux.subscribe(System.out::println);
        //onSubscribe -> request(unbounded) ->
        // onNext(string 1) ->onNext(string 2)->onNext(string 3)->onNext(string 4) -> onNext(string 5)
        //onComplete()
    }

    @Test
    public void fluxTest_onError(){
        Flux<String> flux = Flux.just("string 1", "string 2", "string 3", "string 4")
                .concatWith(Flux.error(new RuntimeException("test erorr messsage")))
                .log();
//        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println,(ex)->System.out.println(ex.getMessage()));

    }
}
