package day12.skating;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WinterDayDemoExecuteService {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Good morning everyone, we are opened");

        final SkatingRinkQueueNotSynchr skatingRink = new SkatingRinkQueueNotSynchr();
        final Random random                         = new Random();
        ExecutorService executorService             = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 50; i++) {

                final Skater skater = new Skater("Skater " + i);

                executorService.submit(new Callable<Object>() {
                                           @Override
                                           public Object call() throws Exception {
                                               Skates skates = skatingRink.getSkates(skater);
                                               sleep(random.nextInt(4000));
                                               skatingRink.returnSkates(skates, skater);
                                               return skatingRink.allowSkates();
                                           }
                                       }
                );

                sleep(random.nextInt(500));

            }
        }finally {
            executorService.shutdown();
        }

    }


    private static void sleep(int timeout){

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
