package com.ht.common.util.util;

import lombok.experimental.UtilityClass;

/**
 * Created on 2016/10/10.
 *
 * @author hutao
 * @version 1.0
 */
@UtilityClass
public class SortUtil {
    /**
     * 快速排序
     *
     * @param list
     * @return
     */
    public static Integer[] quickSort(final Integer[] list) {
        internalQuickSort(list, 0, list.length - 1);
        return list;
    }

    private static void internalQuickSort(final Integer[] list,
                                          final int left,
                                          final int right) {
        if (left < right) {
            final int key = list[left];
            int low = left;
            int high = right;
            while (low < high) {
                while (low < high && list[high] > key) {
                    --high;
                }
                list[low] = list[high];
                while (low < high && list[low] < key) {
                    ++low;
                }
                list[high] = list[low];
            }
            list[low] = key;
            internalQuickSort(list, left, low - 1);
            internalQuickSort(list, low + 1, right);
        }
    }

    public static Integer[] insertSort(final Integer[] list) {

        return list;
    }

    public static Integer[] heapSort(final Integer[] list) {
        for (int i = list.length / 2 - 1; i >= 0; --i) {
            minHeapFixDown(list, i, list.length);
        }
        return list;
    }

    private static void minHeapFixDown(final Integer[] list, int i, final int length) {
        final int tmp = list[i];
        int j = 2 * i + 1;
        while (i < length) {
            if (j + 1 < length && list[j] > list[j + 1]) {
                ++j;
            }
            if (list[j] > tmp) {
                break;
            }
            list[i] = list[j];
            i = j;
            j = 2 * i + 1;
        }
        list[i] = tmp;
    }
}
