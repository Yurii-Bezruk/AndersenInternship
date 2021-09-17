package striker.studing.multithreading.locks;

import java.util.concurrent.locks.ReadWriteLock;

public class Writer extends Thread {
    private final CommonResource resource;
    private final ReadWriteLock lock;

    public Writer(CommonResource resource, ReadWriteLock lock) {
        this.resource = resource;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (! isInterrupted()){
            lock.writeLock().lock();
            resource.value = (int) (Math.random() * 10);
            System.out.println("Writer write value " + resource.value);
            lock.writeLock().unlock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
