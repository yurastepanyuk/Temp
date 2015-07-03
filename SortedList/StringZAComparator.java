package day7.SortedList;

import java.util.Comparator;

/**
 * Created by stepanyuk on 03.07.2015.
 */
public class StringZAComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        int result = o1.compareToIgnoreCase(o2);
        if (result > 0) {
            return -1;
        } else if (result < -1){
            return 1;
        }
        return 0;
    }
}
