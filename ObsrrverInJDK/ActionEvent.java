package day9.ObsrrverInJDK;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class ActionEvent {
    
    public String name;

    public ActionEvent(String name) {

        if (name == null) {
            name = this.toString();
        }

        
        this.name = name;
    }
}
