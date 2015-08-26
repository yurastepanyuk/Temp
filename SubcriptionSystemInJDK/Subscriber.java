package day9.SubcriptionSystemInJDK;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Subscriber implements Observer {
    private String name;

    public Subscriber(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.toString() + ":" + "send MAIL to " + this.getName());
    }
}
