package day10.wildcards;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stepanyuk on 25.08.2015.
 */
public class WildcardsDemo {


    public static void main(String[] args) {

        List <Integer> numsInt = Arrays.asList(new Integer[]{1,1,2});
        print(numsInt);

        List<Long> numsLong = Arrays.asList(new Long[]{1L,1L,2L});
        print(numsLong);

    }


    public static void print(List<? extends Number> numbers) {
        for (Number number : numbers) {

            System.out.println(number.toString());

        }
    }

}
