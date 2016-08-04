package com.ht.test.brave.profile;

import com.github.kristofa.brave.*;
import com.github.kristofa.brave.zipkin.ZipkinSpanCollector;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BraveConfig {
    @Bean
    @Scope(value = "singleton")
    public SpanCollector spanCollector() {
        return new ZipkinSpanCollector("localhost", 9410);
    }

    @Bean
    @Scope(value = "singleton")
    public ClientTracer clientTracer() {
        return Brave.getClientTracer(spanCollector(), Lists.newArrayList(new FixedSampleRateTraceFilter(1)));
    }

    @Bean
    @Scope(value = "singleton")
    public ServerTracer serverTracer() {
        return Brave.getServerTracer(spanCollector(), Lists.newArrayList(new FixedSampleRateTraceFilter(1)));
    }

    @Bean
    public EndPointSubmitter endPointSubmitter() {
        return Brave.getEndPointSubmitter();
    }

    @Bean
    @Scope(value = "singleton")
    public BraveTracer braveTracer() {
        return new BraveTracer(
                clientTracer(),
                serverTracer(),
                endPointSubmitter());
    }
}