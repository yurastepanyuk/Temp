package day12.skating;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SchoolSkatingRink implements SkatingRink {

    private Integer       freeQuantitySkates;
    private Lock          getSkatesLock;

    public SchoolSkatingRink() {
        this.freeQuantitySkates = 4;
        getSkatesLock           = new ReentrantLock();
        System.out.println("Free skates " + freeQuantitySkates);
    }

    @Override
    public Skates getSkates(Skater skater) {
        while (true){
            System.out.println(skater.getName());
            if (!allowSkates()) {
                synchronized (freeQuantitySkates){
                    try {
                        freeQuantitySkates.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                break;
            }
        }
        getSkatesLock.lock();
        freeQuantitySkates -= 1;
        getSkatesLock.unlock();

        System.out.println(skater.getName() + " got skates. Free skates " + freeQuantitySkates);
        return new Skates();

    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {

        freeQuantitySkates += 1;
        System.out.println(skater.getName() + " return skates. Free skates " + freeQuantitySkates);

        synchronized (freeQuantitySkates){
            freeQuantitySkates.notify();
        }

    }

    private boolean allowSkates(){
        return freeQuantitySkates > 0 ? true:false;
    }

}
