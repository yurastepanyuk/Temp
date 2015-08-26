package day10.wildcards;

import day10.domain.AutoParts;
import day10.domain.Oil;
import day10.domain.Tire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 25.08.2015.
 */
public class AutoPartsServiceDemo {

    public static void main(String[] args) {

        List<Tire> tires = getTire();
        List<Oil>  oils  = getOils();

        List<AutoParts> ap = new ArrayList<>();

        GenericSuperAutoPartsService.copy(tires, ap);
        GenericSuperAutoPartsService.copy(oils,ap);

        List<Object> objects = new ArrayList<>();
        GenericSuperAutoPartsService.copy(tires, objects);

    }

    private static List<Oil> getOils(){

        List <Oil> oils = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            oils.add(new Oil());
        }
        return oils;
    }

    private static List<Tire> getTire(){
        List<Tire> tires = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tires.add(new Tire());
        }
        return tires;
    }


}
