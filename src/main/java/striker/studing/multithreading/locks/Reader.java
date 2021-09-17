package striker.studing.multithreading.locks;

import java.util.concurrent.locks.ReadWriteLock;

public class Reader extends Thread {
    private final CommonResource resource;
    private final ReadWriteLock lock;

    public Reader(CommonResource resource, ReadWriteLock lock) {
        this.resource = resource;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (! isInterrupted()){
            lock.readLock().lock();
            System.out.println("Reader read value " + resource.value);
            lock.readLock().unlock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
