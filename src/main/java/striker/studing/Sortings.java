package striker.studing;

public final class Sortings {
    private Sortings(){}

    public static <T extends Comparable<T>> T[] bubbleSort(T[] arr){
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        }
        return arr;
    }
}
