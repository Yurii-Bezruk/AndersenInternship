package striker.studing;

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

    private static <T extends Comparable<T>> void swap(T[] arr, int firstIndex, int secondIndex){
        T temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
