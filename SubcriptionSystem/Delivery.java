package day9.SubcriptionSystem;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public interface Delivery {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
