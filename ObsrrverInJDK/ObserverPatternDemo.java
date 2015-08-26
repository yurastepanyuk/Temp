package day9.ObsrrverInJDK;

import java.util.Observer;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {

        Worker w = new Worker();

        Observer o1 = new Supervisor();
        Observer o2 = new Supervisor();

        w.addObserver(o1);
        w.addObserver(o2);

        for (int i = 0; i < 10; i++) {
            w.doRegularJob();
            System.out.println();
        }

        w.deleteObserver(o1);

        for (int i = 0; i < 10; i++) {
            w.doSpecificJob();
            System.out.println();
        }

    }


}
