package striker.studing.multithreading;

import java.util.concurrent.Phaser;

public class Phasers {
    private static final Phaser phaser = new Phaser(0);

    public static void main(String[] args) {
        Runnable r = () -> {
            phaser.register();
            try {
                Thread.sleep(3000);
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        };
        new Thread(() -> {
            phaser.register();
            try {
                Thread.sleep(0);
                phaser.arriveAndDeregister();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(r).start();
        new Thread(r).start();
        //System.out.println(phaser.getUnarrivedParties());

    }
}
