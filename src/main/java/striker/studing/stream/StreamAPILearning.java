package striker.studing.stream;

import striker.studing.collections.ArrayList;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPILearning {
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
        System.out.println(
                Stream.iterate(0, x -> x + 1)
                        .limit(10)
                        .collect(Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                list -> list.stream()
                                                        .map(Object::toString)
                                                        .reduce("", (x, y) -> y + x)
                                        )).toString()
        );
        Supplier<List<Integer>> factory = StreamAPILearning::factory;
        BiConsumer<List<Integer>, Integer> accumulator = StreamAPILearning::accumulator;
        BinaryOperator<List<Integer>> combiner = StreamAPILearning::combiner;
        Function<List<Integer>, Integer> finisher = StreamAPILearning::finisher;
        System.out.println(
                Stream.generate(() -> 1)
                        .limit(10)
                        .collect(Collector.of(
                                factory,
                                accumulator,
                                combiner,
                                finisher,
                                Collector.Characteristics.UNORDERED)
                        )
                .toString()
        );
    }

    public static List<Integer> factory(){
        return new ArrayList<Integer>();
    }
    public static void accumulator(List<Integer> dest, Integer next){
        dest.add(next);
    }
    public static List<Integer> combiner(List<Integer> first, List<Integer> second){
        first.addAll(second);
        return first;
    }
    public static Integer finisher(List<Integer> list){
        return list.stream().mapToInt(x -> x).sum();
    }

}
