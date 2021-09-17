package striker.studing.multithreading.executors;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {
    public static void main(String[] args) {
        int[] arr = new int[1_000_00];
        Arrays.fill(arr, 1);
        RecursiveTask<Integer> task = new ArraySummerRecursiveTask(arr);
        ForkJoinPool pool = new ForkJoinPool(20);
        long start = System.currentTimeMillis();
        int sum = pool.invoke(task);
        long time = System.currentTimeMillis() - start;
        System.out.println("time : "+time+" sum : "+sum);
    }
}
class ArraySummerRecursiveTask extends RecursiveTask<Integer> {
    private final int[] array;

    public ArraySummerRecursiveTask(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if(array.length <= 2){
            try {
                Thread.sleep(1);
                return Arrays.stream(array).sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int mid = array.length / 2;
        RecursiveTask<Integer> first = new ArraySummerRecursiveTask(Arrays.copyOfRange(array, 0, mid));
        RecursiveTask<Integer> second = new ArraySummerRecursiveTask(Arrays.copyOfRange(array, mid, array.length));
        first.fork();
        second.fork();
        return second.join() + first.join();
    }
}
