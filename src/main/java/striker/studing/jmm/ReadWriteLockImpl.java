package striker.studing.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class ReadWriteLockImpl implements ReadWriteLock {
    private volatile boolean writeLockStatus;
    private final AtomicInteger readLockStatus = new AtomicInteger();
    private final Lock readLock = new ReadLock();
    private final Lock writeLock = new WriteLock();

    @Override
    public Lock readLock() {
        return readLock;
    }
    @Override
    public Lock writeLock() {
        return writeLock;
    }
    class ReadLock implements Lock {
        @Override
        public void lock() throws InterruptedException {
            synchronized (ReadWriteLockImpl.this){
                while (writeLockStatus){
                    ReadWriteLockImpl.this.wait();
                }
                readLockStatus.getAndIncrement();
            }
        }
        @Override
        public void unlock() {
            synchronized (ReadWriteLockImpl.this) {
                readLockStatus.getAndDecrement();
                ReadWriteLockImpl.this.notifyAll();
            }
        }
    }
    class WriteLock implements Lock {
        @Override
        public void lock() throws InterruptedException {
            synchronized (ReadWriteLockImpl.this){
                while (writeLockStatus || readLockStatus.get() > 0){
                    ReadWriteLockImpl.this.wait();
                }
                writeLockStatus = true;
            }
        }
        @Override
        public void unlock() {
            synchronized (ReadWriteLockImpl.this) {
                writeLockStatus = false;
                ReadWriteLockImpl.this.notifyAll();
            }
        }
    }
}
