package striker.studing;

import java.util.Arrays;

public final class Sortings {
    private Sortings(){}

    public static <T extends Comparable<T>> T[] bubbleSort(T[] arr){
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i+1);
                    swapped = true;
                }
            }
        }
        return arr;
    }
    public static <T extends Comparable<T>> T[] selectionSort(T[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min]) < 0){
                    min = j;
                }
            }
            swap(arr, i, min);
        }
        return arr;
    }
    public static <T extends Comparable<T>> T[] insertionSort(T[] arr){
        for (int i = 0; i < arr.length; i++) {
            T cursor = arr[i];
            int position;
            for (position = i; (position > 0) && (arr[position - 1].compareTo(cursor) > 0); position--) {
                arr[position] = arr[position - 1];
            }
            arr[position] = cursor;
        }
        return arr;
    }
    public static <T extends Comparable<T>> T[] mergingSort(T[] arr){
        if(arr.length <= 1){
            return arr;
        }
        int middle = arr.length / 2;
        T[] left = mergingSort(Arrays.copyOfRange(arr, 0, middle));
        T[] right = mergingSort(Arrays.copyOfRange(arr, middle, arr.length));
        return merge(left, right, Arrays.copyOf(arr, arr.length));
    }
    private static <T extends Comparable<T>> T[] merge(T[] left, T[] right, T[] merged){
        int leftCursor = 0, rightCursor = 0;
        while (leftCursor < left.length && rightCursor < right.length){
            if(left[leftCursor].compareTo(right[rightCursor]) < 0){
                merged[leftCursor + rightCursor] = left[leftCursor];
                leftCursor++;
            }
            else {
                merged[leftCursor + rightCursor] = right[rightCursor];
                rightCursor++;
            }
        }
        for (; leftCursor < left.length; leftCursor++) {
            merged[leftCursor + rightCursor] = left[leftCursor];
        }
        for (; rightCursor < right.length; rightCursor++) {
            merged[leftCursor + rightCursor] = right[rightCursor];
        }
        return merged;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int firstIndex, int secondIndex){
        T temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
