package striker.studing.multithreading.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Exchanger;

public class Exchangers {
    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            //Thread.currentThread().setDaemon(true);
            String str = "first";
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " received " + str);
        }).start();
        new Thread(()->{
            //Thread.currentThread().setDaemon(true);
            String str = "second";
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " received " + str);
        }).start();
    }
}
