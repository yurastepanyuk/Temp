package day12.skating;

import java.util.Random;

public class WinterDayDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Good morning everyone, we are opened");

        //final SchoolSkatingRink   skatingRink = new SchoolSkatingRink();
        //final SkatingRinkQueue skatingRink = new SkatingRinkQueue();
        final SkatingRinkQueueNotSynchr skatingRink = new SkatingRinkQueueNotSynchr();
        final Random random                         = new Random();

        for (int i = 0; i < 50; i++) {

            final Skater skater = new Skater("Skater " + i);

            Thread threadSkates = new Thread(new Runnable() {
                @Override
                public void run() {
                    Skates skates = skatingRink.getSkates(skater);
                    sleep(random.nextInt(4000));
                    skatingRink.returnSkates(skates, skater);
                }
            });

            threadSkates.start();
            threadSkates.join();
            sleep(random.nextInt(500));

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
