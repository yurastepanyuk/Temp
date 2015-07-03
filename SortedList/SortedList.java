package day7.SortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by stepanyuk on 03.07.2015.
 */
public class SortedList {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Yura");
        names.add("Katya");
        names.add("Sergij");
        names.add("Valera");
        names.add("Sasha");
        names.add("Olya");
        names.add("Vova");
        names.add("Svyatoslav");
        names.add("Dima");
        names.add("Maksim");

        Collections.sort(names);
        for (String name : names){
            System.out.println(name);
        }

        Collections.sort(names, new StringZAComparator());

        System.out.println();
        System.out.println();
        for (String name : names){
            System.out.println(name);
        }

        //comparator anonymous class

        Collections.sort(names, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = (String)o1;
                String s2 = (String)o2;
                int result = s1.compareToIgnoreCase(s2);
                if (result > 0){
                    return 1;
                } else if (result < 0){
                    return -1;
                }
                return 0;
            }
        });

        System.out.println();
        System.out.println();
        for (String name : names){
            System.out.println(name);
        }

    }


}
