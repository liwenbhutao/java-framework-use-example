package com.ht.common.util.util;

import org.testng.annotations.Test;

/**
 * Created on 2016/10/10.
 *
 * @author hutao
 * @version 1.0
 */
public class SortUtilTest {
    @org.testng.annotations.Test
    public void testName() throws Exception {
        final Integer[] list = new Integer[]{1, 5, 6, 3, 7};
        SortUtil.quickSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    @Test
    public void testName1() throws Exception {
        final String s1 = "Programming";
        final String s2 = new String("Programming");
        final String s4 = new String("Programming");
        final String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s2.intern());
        System.out.println(s2 == s4);
        System.out.println(s2.intern() == s4.intern());
        System.out.println("ja" + "va" == "java");
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());

    }

    @Test
    public void testName3() throws Exception {
        final Integer i1 = new Integer(1);
        final Integer i2 = new Integer(1);
        final Integer i3 = 1;
        final Integer i5 = 1;
        final Integer i6 = 129;
        final Integer i7 = 129;
        final Integer i4 = Integer.valueOf(1);
        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i1 == 1);
        System.out.println(i4 == i3);
        System.out.println(i5 == i3);
        System.out.println(i6 == i7);
    }

    interface A {
        int a = 10;

        void a();
    }
}