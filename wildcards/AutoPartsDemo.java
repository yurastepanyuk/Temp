package day10.wildcards;

import day10.domain.AutoParts;
import day10.domain.Oil;
import day10.domain.Tire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 25.08.2015.
 */
public class AutoPartsDemo {

    public static void main(String[] args) {
        AutoPartsBox<AutoParts> products = new AutoPartsBox<>();
        AutoPartsBox<Oil>       oils     = new AutoPartsBox<>();
        AutoPartsBox<Tire>      tires    = new AutoPartsBox<>();

        products.add(new Oil());
        products.add(new Tire());

        List<AutoParts> aps = products.getAutoParts();

        System.out.println(aps.toString());
    }

}
