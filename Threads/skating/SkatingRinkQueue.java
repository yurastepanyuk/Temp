package day12.skating;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SkatingRinkQueue implements SkatingRink {

    private       Integer        freeQuantitySkates;
    private final Queue<Integer> skatesThreadLock   = new LinkedBlockingQueue<>();

    public SkatingRinkQueue() {
        this.freeQuantitySkates = 4;
        System.out.println("Free skates " + freeQuantitySkates);
    }

    @Override
    public Skates getSkates(Skater skater) {
        while (true){
            System.out.println(skater.getName());
            if (!allowSkates()) {
                skatesThreadLock.add(freeQuantitySkates);
                synchronized (skatesThreadLock){
                    try {
                        skatesThreadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                break;
            }
        }

        freeQuantitySkates -= 1;
        skatesThreadLock.poll();

        System.out.println(skater.getName() + " got skates. Free skates " + freeQuantitySkates);
        return new Skates();

    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {

        freeQuantitySkates += 1;
        System.out.println(skater.getName() + " return skates. Free skates " + freeQuantitySkates);

        synchronized (skatesThreadLock){
            skatesThreadLock.notify();
        }

    }

    private boolean allowSkates(){
        return freeQuantitySkates > 0 ? true:false;
    }

}
