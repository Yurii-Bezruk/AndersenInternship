package striker.studing.hippodrome;

import java.util.Comparator;
import java.util.List;

public class SeqHippodrome implements Hippodrome {
    private List<SeqHorse> horses;

    public SeqHippodrome(List<SeqHorse> horses) {
        this.horses = horses;
    }

    public List<SeqHorse> getHorses() {
        return horses;
    }

    public void run(){
        for (int i = 0; i < 50; i++) {
            move();
            print();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for (SeqHorse horse : horses) {
            horse.move();
        }
    }
    public void print(){
        for (SeqHorse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public SeqHorse getWinner(){
        return horses.stream().max(Comparator.comparingDouble(SeqHorse::getDistance)).get();
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName());
    }
}
