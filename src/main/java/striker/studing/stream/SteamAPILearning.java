package striker.studing.stream;

import java.util.function.*;

public class SteamAPILearning {
    Consumer<Integer> consumer = this::consumer;
    Supplier<Integer> supplier = this::supplier;
    Predicate<Integer> predicate = this::predicate;
    UnaryOperator<Integer> unaryOperator = this::unaryOperator;
    Function<Integer, String> function = this::function;

    public void consumer(Integer a){
        System.out.println(a);
    }
    public Integer supplier(){
        return 5;
    }
    public boolean predicate(Integer a){
        return a > 0;
    }
    public Integer unaryOperator(Integer a){
        return a + 1;
    }
    public String function(Integer a){
        return a.toString();
    }

    public static void main(String[] args) {

    }
}
