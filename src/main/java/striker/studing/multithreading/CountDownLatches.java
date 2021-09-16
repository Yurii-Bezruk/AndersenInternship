package striker.studing.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatches {
    private static final Object monitor = new Object();
    private static final CountDownLatch lock = new CountDownLatch(4);

    public static void main(String[] args) {
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();
        try {
            lock.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Runnable{

        @Override
        public void run() {
            synchronized (monitor){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.countDown();
            }
        }
    }
}
