package day9.AdapterPattern;

/**
 * Created by stepanyuk on 06.08.2015.
 */
public class AdapterByObject implements NewInterface_Target {

    private OldClassAdaptee adaptor;

    public AdapterByObject() {
        this.adaptor = new OldClassAdaptee();
    }

    @Override
    public String newRequest() {
        return adaptor.oldRequest();
    }
}
