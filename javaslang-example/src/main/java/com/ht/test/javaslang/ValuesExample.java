package com.ht.test.javaslang;

import javaslang.Lazy;
import javaslang.collection.CharSeq;
import javaslang.collection.List;
import javaslang.control.Try;
import javaslang.control.Validation;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/24.
 * 上午10:58
 */
@Slf4j
public class ValuesExample implements Runnable {
    @Override
    public void run() {
        useTry();
        useLazy();
        useEither();
        useValidation();
    }

    private void useValidation() {
        final PersonValidator personValidator = new PersonValidator();

        final Validation<List<String>, Person> valid = personValidator.validatePerson("John Doe", 30);

        log.info("validation valid result:{},person:{}", valid.isValid(), valid.get());
        final Validation<List<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);
        log.info("validation invalid result:{},error:{}", invalid.isValid(), invalid.getError());
    }

    private void useEither() {
    }

    private void useLazy() {
        final Lazy<Double> lazy = Lazy.of(Math::random);
        log.info("lazy before get:{}", lazy.isEvaluated());
        lazy.get();
        log.info("lazy after get:{}", lazy.isEvaluated());
        lazy.get();

    }

    private void useTry() {
        Try.of(() -> bunchOfWork(true)).getOrElse(2);
        log.info("try result1:{},expect 2", Try.of(() -> bunchOfWork(true)).getOrElse(2));
        log.info("try result1:{},expect 1", Try.of(() -> bunchOfWork(false)).getOrElse(2));
    }

    private int bunchOfWork(final boolean needThrowException) {
        if (needThrowException) {
            throw new RuntimeException("error");
        }
        return 1;
    }

    private static class PersonValidator {

        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_AGE = 0;

        public Validation<List<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                    ? Validation.valid(name)
                    : Validation.invalid("Name contains invalid characters: '"
                    + seq.distinct().sorted() + "'"));
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < MIN_AGE
                    ? Validation.invalid("Age must be greater than " + MIN_AGE)
                    : Validation.valid(age);
        }

    }

    private static class Person {

        public final String name;
        public final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person(" + name + ", " + age + ")";
        }

    }
}
