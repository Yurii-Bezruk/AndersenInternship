package striker.studing.jmm;

public class LockImpl implements Lock{
    private boolean locked;

    @Override
    public void lock() {
        synchronized (this){
            while (locked){
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            locked = true;
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            locked = false;
            notifyAll();
        }
    }
}
