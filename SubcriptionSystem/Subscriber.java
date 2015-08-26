package day9.SubcriptionSystem;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Subscriber implements Observer{
    private String name;

    public Subscriber(String name){
        this.name = name;
    }

    public void update(Object obg){
        System.out.println(obg.toString() + ":" + "send MAIL to " + this.getName());
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName(){
        return this.name;
    }
}
