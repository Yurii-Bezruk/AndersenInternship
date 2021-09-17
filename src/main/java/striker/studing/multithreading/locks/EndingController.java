package striker.studing.multithreading.locks;

public class EndingController implements EndingControllerMBean {
    private boolean running = true;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean status) {
        this.running = status;
    }
}
