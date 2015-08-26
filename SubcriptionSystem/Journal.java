package day9.SubcriptionSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Journal implements Delivery {

    private String name;

    private List<Observer> observers;

    public Journal(String name){
        observers = new ArrayList<>();
        this.name = name;
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){

    }

    public void notifyObservers(){

        for (Observer o : observers) {
            o.update(this);
        }

    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName(){
        return this.name;
    }
}
