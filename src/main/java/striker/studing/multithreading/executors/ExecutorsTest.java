package striker.studing.multithreading.executors;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorsTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            Thread.sleep((long) (Math.random() * 10_000));
            return Thread.currentThread().getName();
        };
        List<Callable<String>> tasks = List.of(callable, callable, callable);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> runnableTasks = executor.invokeAll(tasks);
        for (Future<String> task : runnableTasks) {
            System.out.println(task.get());
        }
        executor.shutdown();
    }
}
