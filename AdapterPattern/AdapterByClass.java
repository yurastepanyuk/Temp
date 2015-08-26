package day9.AdapterPattern;

/**
 * Created by stepanyuk on 06.08.2015.
 */
public class AdapterByClass extends OldClassAdaptee implements NewInterface_Target{
    @Override
    public String newRequest() {
        return oldRequest();
    }
}
