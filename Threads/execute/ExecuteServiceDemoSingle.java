package day12.execute;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteServiceDemoSingle {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try {
            Future<Integer> future = executorService.submit(new Task());
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }



    }

}
