package striker.studing.jmm;

public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();

    static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReadWriteLockImpl();
        int[] counter = new int[1];
        Runnable writer = ()->{
            while (! Thread.currentThread().isInterrupted()) {
                try {
                    lock.writeLock().lock();
                    System.out.println("writer write " + ++counter[0]);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
                finally {
                    lock.writeLock().unlock();
                }
            }
        };
        Runnable reader = ()->{
            while (! Thread.currentThread().isInterrupted()) {
                try {
                    lock.readLock().lock();
                    System.out.println("reader "+Thread.currentThread().getName()+" read " + counter[0]);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
                finally {
                    lock.readLock().unlock();
                }
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(reader);
        }
        Thread writerThread = new Thread(writer);
        writerThread.start();
        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(10_000);
        writerThread.interrupt();
        writerThread.join();
        for (Thread thread : threads) {
            thread.interrupt();
            thread.join();
        }
        System.out.println(counter[0]);
    }
}
