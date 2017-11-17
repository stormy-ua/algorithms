package org.examples.algorithms;

import java.util.Arrays;

public class HeapUtils {

    private static int parent(int i) {
        return i>>1;
    }

    private static int left(int i) {
        return (i<<1) + 1;
    }

    private static int right(int i) {
        return (i<<1) + 2;
    }

    private static <T> void swap(T[] input, int x, int y) {
        T tmp = input[x];
        input[x] = input[y];
        input[y] = tmp;
    }

    public static <T extends Comparable<T>> void maxHeapify(T[] input, int heapSize, int index) {
        int l = left(index);
        int r = right(index);
        int largest = index;

        if (l < heapSize && input[l].compareTo(input[index]) > 0) {
            largest = l;
        }

        if(r < heapSize && input[r].compareTo(input[largest]) > 0) {
            largest = r;
        }

        if (largest != index) {
            swap(input, index, largest);
            maxHeapify(input, heapSize, largest);
        }
    }

    public static <T extends Comparable<T>> void buildMaxHeap(T[] input) {

        for (int i = input.length / 2; i >= 0; --i) {
            maxHeapify(input, input.length, i);
        }

    }

    public static<T extends Comparable<T>> void heapSort(T[] input) {
        buildMaxHeap(input);

        for (int i = input.length - 1; i > 0; --i) {
            swap(input, i, 0);
            maxHeapify(input, i, 0);
        }
    }

    public static void main(String[] args) {

        System.out.println(1 << 1);

        // max-heapify
        Integer[] input = { 10, 1, 8, 9, 2, 3, 4, 5 };
        System.out.println(Arrays.toString(input));

        maxHeapify(input, input.length, 1);
        System.out.println(Arrays.toString(input));

        // build-max-heap
        Integer[] input2 = { 0, 12, 1, 2, 5, 3, 13, 4, 15 };
        System.out.println(Arrays.toString(input2));

        buildMaxHeap(input2);
        System.out.println(Arrays.toString(input2));

        // heapsort
        System.out.println(Arrays.toString(input2));

        heapSort(input2);
        System.out.println(Arrays.toString(input2));
    }
}
