package striker.studing.jmm;

public class LockImpl implements Lock{
    private boolean locked;

    @Override
    public synchronized void lock() {
        while (locked){
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        locked = true;
    }

    @Override
    public synchronized void unlock() {
        locked = false;
        notifyAll();
    }
}
