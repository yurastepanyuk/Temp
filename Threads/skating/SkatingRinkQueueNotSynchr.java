package day12.skating;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SkatingRinkQueueNotSynchr implements SkatingRink {

    private       Integer        freeQuantitySkates;
    private final Queue<Integer> skatesThreadLock   = new LinkedBlockingQueue<>();

    public SkatingRinkQueueNotSynchr() {
        this.freeQuantitySkates = 4;
        System.out.println("Free skates " + freeQuantitySkates);
    }

    @Override
    public Skates getSkates(Skater skater) {
        while (true){
            System.out.println(skater.getName());
            if (!allowSkates()) {
                skatesThreadLock.add(freeQuantitySkates);
            } else {
                break;
            }
            sleep(100);
        }

        freeQuantitySkates -= 1;

        System.out.println(skater.getName() + " got skates. Free skates " + freeQuantitySkates);
        return new Skates();

    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {

        freeQuantitySkates += 1;
        System.out.println(skater.getName() + " return skates. Free skates " + freeQuantitySkates);

    }

    public boolean allowSkates(){
        return freeQuantitySkates > 0 ? true:false;
    }

    private void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
