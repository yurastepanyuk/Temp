package day9.SubcriptionSystemInJDK;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Journal extends Observable {

    private String name;


    public Journal(String name){
        this.name = name;
    }

    public void informSubscribers(){
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName(){
        return this.name;
    }
}
