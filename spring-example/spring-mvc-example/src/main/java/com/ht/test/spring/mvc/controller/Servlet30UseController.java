package com.ht.test.spring.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created on 16/6/21.
 *
 * @author hutao
 * @version 1.0
 */
@RestController
@RequestMapping("/servlet30")
@Slf4j
public class Servlet30UseController {
    private final Queue<DeferredResult<String>> responseBodyQueue = new ConcurrentLinkedQueue<>();
    private final Queue<DeferredResult<String>> exceptionQueue = new ConcurrentLinkedQueue<>();


    @RequestMapping("/a")
    public
    @ResponseBody
    DeferredResult<String> deferredResult() {
        final DeferredResult<String> result = new DeferredResult<>();
        this.responseBodyQueue.add(result);
        return result;
    }

    @RequestMapping("/b")
    public
    @ResponseBody
    DeferredResult<String> deferredResultWithException() {
        final DeferredResult<String> result = new DeferredResult<>();
        this.exceptionQueue.add(result);
        return result;
    }

    @RequestMapping("/c")
    public
    @ResponseBody
    DeferredResult<String> deferredResultWithTimeoutValue() {

        // Provide a default result in case of timeout and override the timeout value
        // set in src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml

        return new DeferredResult<>(1000L, "Deferred result after timeout");
    }

    @Scheduled(fixedRate = 2000)
    public void processQueues() {
        for (final DeferredResult<String> result : this.responseBodyQueue) {
            log.info("set deferred result");
            result.setResult("Deferred result");
            this.responseBodyQueue.remove(result);
        }
        for (final DeferredResult<String> result : this.exceptionQueue) {
            log.info("set deferred exception result");
            result.setErrorResult(new IllegalStateException("DeferredResult error"));
            this.exceptionQueue.remove(result);
        }
    }

    @ExceptionHandler
    @ResponseBody
    public String handleException(final IllegalStateException ex) {
        return "Handled exception: " + ex.getMessage();
    }

}
