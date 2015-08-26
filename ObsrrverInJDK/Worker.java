package day9.ObsrrverInJDK;

import java.util.Observable;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Worker extends Observable {

    private double jobResult;

    public Worker() {
    }

    public void doRegularJob(){
        double d = Math.random();
        if (d <= 0.5 || d >= 10) {
            System.out.println("Regular Job done!");
            jobResult = d;
            setChanged();
            notifyObservers();
        } else{
            System.out.println("Regular Job filed");
        }
    }

    public void doSpecificJob(){
        double d = Math.random();

        if (d <= 0.5 || d >= 10) {
            System.out.println("Special Job done!");
            jobResult = d;
            setChanged();
            notifyObservers(new ActionEvent(null));
        } else {
            System.out.println("Special Job filed!");
        }

    }

    public double getJobResult() {
        return jobResult;
    }
}
