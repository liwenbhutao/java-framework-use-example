package com.ht.test.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ht.test.guice.support.AModule;
import com.ht.test.guice.support.AService;
import com.ht.test.guice.support.AService1;

/**
 * Created by hutao on 16/5/25.
 * 下午1:34
 */
public class GuiceExample implements Runnable {
    @Override
    public void run() {
        final Injector injector = Guice.createInjector(new AModule());
        final AService aService = injector.getInstance(AService.class);
        aService.run();

        final AService aService1 = new AService1();
        injector.injectMembers(aService1);
        aService1.run();
    }


}
