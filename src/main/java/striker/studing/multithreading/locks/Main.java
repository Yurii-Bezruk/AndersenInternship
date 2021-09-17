package striker.studing.multithreading.locks;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CommonResource resource = new CommonResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Writer writer = new Writer(resource, lock);
        writer.setName("writer");
        Reader reader1 = new Reader(resource, lock);
        reader1.setName("reader 1");
        Reader reader2 = new Reader(resource, lock);
        reader1.setName("reader 1");
        Reader reader3 = new Reader(resource, lock);
        reader1.setName("reader 1");
        List<Thread> threads = List.of(writer, reader1, reader2, reader3);
        threads.forEach(Thread::start);
        Thread.sleep(5_000);
        threads.forEach(Thread::interrupt);
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
