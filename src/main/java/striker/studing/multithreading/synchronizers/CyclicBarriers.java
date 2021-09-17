package striker.studing.multithreading.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarriers {
    private static final CyclicBarrier barrier = new CyclicBarrier(4);

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep((long) (Math.random() * 1000));
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
