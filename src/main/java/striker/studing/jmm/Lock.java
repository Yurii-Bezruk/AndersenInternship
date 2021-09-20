package striker.studing.jmm;

import java.util.stream.Stream;

public interface Lock {
    void lock();
    void unlock();

    static void main(String[] args) throws InterruptedException {
        Lock lock = new LockImpl();
        int[] counter = new int[1];
        Runnable r = ()->{
            lock.lock();
            System.out.println("sync code " + Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter[0]++;
            lock.unlock();
        };
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(counter[0]);
    }
}
