package day9.SubcriptionSystemInJDK;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class NewsPaper extends Observable {

    private String name;

    public NewsPaper(String name){
        this.name = name;
    }

    public void informSubscribers(){
        setChanged();
        notifyObservers();
    }

    public String toString(){
        return getName();
    }

    public String getName(){
        return this.name;
    }
}
