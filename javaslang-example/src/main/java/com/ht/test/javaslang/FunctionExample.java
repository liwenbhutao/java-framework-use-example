package com.ht.test.javaslang;

import javaslang.Function0;
import javaslang.Function1;
import javaslang.Function2;
import javaslang.Function3;
import javaslang.control.Option;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/24.
 * 上午10:57
 */
@Slf4j
public class FunctionExample implements Runnable {
    @Override
    public void run() {
        useFunction();
    }

    private void useFunction() {
        useNormalFunction();

        useComposeFunction();

        useLiftFunction();

        useCurriedFunction();

        useMemoizedFunction();
    }

    private void useMemoizedFunction() {
        final Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();
        log.info("memoized function return result is equal:{}", randomValue1 == randomValue2);
    }

    private void useCurriedFunction() {
        final Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        final Function1<Integer, Integer> add2 = sum.curried().apply(2);

        log.info("curried function result:{}", add2.apply(4));
    }

    private void useLiftFunction() {
        final Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        final Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
        log.info("lift function 1/0 is empty:{}", safeDivide.apply(1, 0).isEmpty());
        log.info("lift function 4/2 is empty:{}", safeDivide.apply(4, 2).isEmpty());
    }

    private void useComposeFunction() {
        final Function1<Integer, Integer> plusOne = a -> a + 1;
        final Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        final Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        log.info("compose function result:{},expect result is 6", add1AndMultiplyBy2.apply(2));
    }

    private void useNormalFunction() {
        final Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        log.info("sum function result:{}", sum.apply(1, 2));
        log.info("3 arg method function result:{}",
                Function3.of(this::methodWhichAccepts3Parameters).apply("a", "b", "c"));
    }

    private String methodWhichAccepts3Parameters(final String arg1, final String arg2, final String arg3) {
        return arg1 + arg2 + arg3;
    }
}
