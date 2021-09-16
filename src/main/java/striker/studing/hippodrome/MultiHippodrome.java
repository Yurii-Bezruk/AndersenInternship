package striker.studing.hippodrome;


import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MultiHippodrome implements Hippodrome {
    private List<MultiHorse> horses;

    public MultiHippodrome(List<MultiHorse> horses) {
        this.horses = horses;
    }

    @Override
    public void run() {
        Thread[] threads = new Thread[horses.size()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(horses.get(i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        while (Stream.of(threads).anyMatch(Thread::isAlive)){
            for (MultiHorse horse : horses) {
                horse.print();
            }
            System.out.println('\n');
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void printWinner() {
        System.out.println(Collections.max(horses).getName());
    }
}
