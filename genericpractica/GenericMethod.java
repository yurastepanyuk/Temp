package day10.genericpractica;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stepanyuk on 21.08.2015.
 */
public class GenericMethod {

    public static void main(String[] args) {
        List<String> cats = Arrays.asList(new String[]{"Murka","Pirat","Vaska"});
        print(cats);
    }


    public static <T> void print(List<T> elements){
        for (T element:elements) {
            System.out.println(element.toString());
        }
    }
}
