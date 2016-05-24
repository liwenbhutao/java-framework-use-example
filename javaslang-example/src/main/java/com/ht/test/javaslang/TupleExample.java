package com.ht.test.javaslang;

import javaslang.Tuple;
import javaslang.Tuple2;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/24.
 * 上午10:25
 */
@Slf4j
public class TupleExample implements Runnable {
    @Override
    public void run() {
        useTuple();
    }

    private void useTuple() {
        final Tuple2<String, Integer> tuple2 = Tuple.of("Java", 8);
        log.info("tuple2._1:{}", tuple2._1);
        log.info("tuple2._2:{}", tuple2._2);
        final Tuple2<String, Integer> tuple21 = tuple2.map(
                s -> s + "slang",
                i -> i / 4
        );
        log.info("tuple21._1:{}", tuple21._1);
        log.info("tuple21._2:{}", tuple21._2);
        final Tuple2<String, Integer> tuple22 = tuple2.map(
                (s, i) -> Tuple.of(s + "slang", i / 4)
        );
        log.info("tuple22._1:{}", tuple22._1);
        log.info("tuple22._2:{}", tuple22._2);
        final String that = tuple2.transform(
                (s, i) -> s + "slang " + i / 4
        );
        log.info("tuple2 transform:{}", that);
    }
}
