package striker.studing.multithreading.synchronizers;

import striker.studing.collections.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Semaphores {
    public static final int BUFFER_SIZE = 5;
    public static List<Integer> buffer = new ArrayList<>();
    public static final Semaphore semaphore = new Semaphore(1);
    public static final Semaphore emptyItems = new Semaphore(BUFFER_SIZE);
    public static final Semaphore fullItems = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        Supplier supplier = new Supplier();
        Consumer consumer = new Consumer();
        supplier.start();
        consumer.start();

        Thread.sleep(5_000);
        supplier.interrupt();
        consumer.interrupt();
        supplier.join();
        consumer.join();
    }
    static class Supplier extends Thread {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    emptyItems.acquire();
                    semaphore.acquire();
                    int elem = (int) (Math.random() * 9 + 1);
                    buffer.add(elem);
                    System.out.print("Produces produce "+elem + " [");
                    buffer.forEach(x -> System.out.print(x + ", "));
                    System.out.println("]");
                    semaphore.release();
                    fullItems.release();
                }
            } catch (InterruptedException ignored){}
        }
    }
    static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    fullItems.acquire();
                    semaphore.acquire();
                    int elem = buffer.remove(buffer.size() - 1);
                    System.out.print("Consumer consume " + elem + " [");
                    buffer.forEach(x -> System.out.print(x + ", "));
                    System.out.println("]");
                    semaphore.release();
                    emptyItems.release();
                }
            } catch (InterruptedException ignored){}
        }
    }
}
