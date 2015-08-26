package day9.AdapterPattern;

/**
 * Created by stepanyuk on 06.08.2015.
 */
public class Demo {

    public static void main(String[] args) {
        NewInterface_Target target = new AdapterByObject();

        System.out.println(target.newRequest());
    }





}
