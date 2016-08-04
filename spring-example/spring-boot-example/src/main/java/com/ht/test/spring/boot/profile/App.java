package com.ht.test.spring.boot.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.stereotype.Component;
import org.stagemonitor.core.Stagemonitor;
import org.stagemonitor.web.WebPlugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by hutao on 16/6/1.
 * 下午11:53
 */
@SpringBootApplication
public class App {

    public static void main(final String[] args) throws Exception {
        Stagemonitor.init();
        SpringApplication.run(App.class, args);
    }

    @Component
    public static class StagemonitorEnabler implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(final ConfigurableEmbeddedServletContainer container) {
            container.addInitializers(new ServletContextInitializer() {
                @Override
                public void onStartup(final ServletContext servletContext) throws ServletException {
                    new WebPlugin().onStartup(null, servletContext);
                }
            });
        }
    }
}