package day9.SubcriptionSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class NewsPaper implements Delivery {
    private List<Observer> observers;

    private String name;

    public NewsPaper(String name){
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

    public String toString(){
        return getName();
    }

    public String getName(){
        return this.name;
    }
}
