package day10.wildcards;

import day10.domain.AutoParts;

import java.util.List;

/**
 * Created by stepanyuk on 25.08.2015.
 */
public class GenericSuperAutoPartsService {


    public static void copy(List<? extends AutoParts> from, List<? super AutoParts> where){
        for (AutoParts bird : from) {

            where.add(bird);

        }
    }



}
