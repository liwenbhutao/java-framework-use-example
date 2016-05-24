package com.ht.test.apache.common.math;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

/**
 * Created by hutao on 16/5/24.
 * 下午2:00
 */
@Slf4j
public class StatisticsExample implements Runnable {
    @Override
    public void run() {
        useStatistics();

        useFrequency();
    }

    private void useFrequency() {
        Frequency f = new Frequency();
        f.addValue(1);
        f.addValue(new Integer(1));
        f.addValue(new Long(1));
        f.addValue(2);
        f.addValue(new Integer(-1));
        log.info("Frequency:{}", f);

        Frequency f1 = new Frequency(String.CASE_INSENSITIVE_ORDER);
        f1.addValue("one");
        f1.addValue("One");
        f1.addValue("oNe");
        f1.addValue("Z");
        System.out.println(f1.getCount("one")); // displays 1
        System.out.println(f1.getCumPct("Z"));  // displays 0.5
        System.out.println(f1.getCumPct("Ot")); // displays 0.25
    }

    private void useStatistics() {
        final List<Integer> integers = Lists.newArrayList(1, 5, 10, 11);
        final DescriptiveStatistics stats = new DescriptiveStatistics();
        stats.setWindowSize(100);
        integers.forEach(stats::addValue);
        log.info("stat:{}", stats);
    }
}
