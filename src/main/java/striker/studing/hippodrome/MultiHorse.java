package striker.studing.hippodrome;

public class MultiHorse extends SeqHorse implements Horse, Runnable, Comparable<MultiHorse> {
    public MultiHorse(String name, double speed, double distance) {
        super(name, speed, distance);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            move();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int compareTo(MultiHorse o) {
        return Double.compare(getDistance(), o.getDistance());
    }
}
