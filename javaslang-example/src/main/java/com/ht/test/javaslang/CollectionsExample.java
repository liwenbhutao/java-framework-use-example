package com.ht.test.javaslang;


import javaslang.collection.List;
import javaslang.collection.Stream;
import lombok.extern.slf4j.Slf4j;

import static javaslang.API.*;
import static javaslang.Predicates.isIn;

/**
 * Created by hutao on 16/5/24.
 * 上午11:37
 */
@Slf4j
public class CollectionsExample implements Runnable {
    @Override
    public void run() {
        useStream();
        useList();
        usePattern();
    }

    private void usePattern() {
        log.info("match 1 result:{}", Match(1).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        ));
        log.info("match 2 result:{}", Match(4).of(
                Case(isIn(3, 4), "three"),
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        ));

        Match("-h").of(
                Case(isIn("-h", "--help"), javaslang.API.run(this::displayHelp)),
                Case(isIn("-v", "--version"), javaslang.API.run(this::displayVersion)),
                Case($(), javaslang.API.run(() -> {
                    throw new IllegalArgumentException("dafa");
                }))
        );
    }

    private void displayHelp() {

    }

    private void displayVersion() {

    }

    private void useList() {
        log.info("List sum result:{}", List.of(1, 2, 3).sum());
    }

    private void useStream() {
        for (final double random : Stream.continually(Math::random).take(10)) {
            log.info("Stream continually result:{} ", random);
        }
        log.info("Stream from filter result1:{}", Stream.from(1).filter(i -> i % 2 == 0));
        log.info("Stream from filter result2:{}", Stream.from(1).filter(i -> i % 2 == 1));
    }
}
