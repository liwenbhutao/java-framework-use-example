package com.ht.test.brave;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 16/8/1.
 *
 * @author hutao
 * @version 1.0
 */
public class App {
    public static void main(final String[] args) {
        final String[] configLocations = new String[]{"classpath:spring_application.xml"};
        final AbstractApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        final AService aService = context.getBean(AService.class);
        aService.fun1();
        aService.echo("hello");
        aService.echo("hello");
        aService.echo("hello");
        aService.echo("hello");
    }
}
