package striker.studing.hippodrome;

import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
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
        for (Horse horse : horses) {
            horse.move();
        }
    }
    public void print(){
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner(){
        return horses.stream().max(Comparator.comparingDouble(Horse::getDistance)).get();
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName());
    }

    public static void main(String[] args) {
        game = new Hippodrome(List.of(
                new Horse("Berta", 3, 0),
                new Horse("Marta", 3, 0),
                new Horse("Lily", 3, 0)
        ));
        game.run();
        game.printWinner();
    }
}
