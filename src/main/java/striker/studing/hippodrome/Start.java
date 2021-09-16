package striker.studing.hippodrome;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        Hippodrome game = new MultiHippodrome(List.of(
                new MultiHorse("Berta", 3, 0),
                new MultiHorse("Marta", 3, 0),
                new MultiHorse("Lily", 3, 0),
                new MultiHorse("Gven", 3.1, 0)
        ));
        game.run();
        game.printWinner();
    }
}
