package com.ht.test.guice.support;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * Created by hutao on 16/5/25.
 * 下午1:38
 */
public class AModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BService.class).to(BService1.class);
        bind(CService.class).annotatedWith(Names.named("CService1")).to(CService1.class);
        bind(AService.class).to(AService1.class);
        bind(DService.class).annotatedWith(AAnotation.class).to(DService1.class);
        bind(DService.class).to(DService2.class);
        bind(Integer.class).annotatedWith(Names.named("Integer1")).toInstance(10);
        bind(Integer.class).annotatedWith(Names.named("Integer2")).toInstance(20);
        bind(FService.class).toProvider(FServiceProvider.class).in(Singleton.class);
    }

    @Provides
    @AAnotation
    EService getEService1() {
        return new EService1();
    }

    @Provides
    @Singleton
    EService getEService2() {
        return new EService2();
    }
}
