package day12.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteServiceDemoPool {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future> futures = new ArrayList<>();

        List<Integer> results = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {

                futures.add(executorService.submit(new Task()));

            }

                for (Future<Integer> curFuture : futures) {
                    if (curFuture.isDone()) {
                        System.out.println(curFuture.get());
                    } else {
                        synchronized (futures){
                            futures.wait();
                        }
                    }
                }

            synchronized (futures){
                futures.notifyAll();
            }


        } finally {
            executorService.shutdown();
        }
        for (Integer result : results){
            System.out.println(result);
        }




    }

}
