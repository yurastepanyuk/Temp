package day9.ObsrrverInJDK;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Supervisor implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof ActionEvent) {
            System.out.println(this.toString() + " notified. Event " + ((ActionEvent)arg).toString());
        }else{
            System.out.println(this.toString() + " notified");
        }

        Worker worker = (Worker) o;
        System.out.println("Job Result: " + worker.getJobResult());

    }
}
