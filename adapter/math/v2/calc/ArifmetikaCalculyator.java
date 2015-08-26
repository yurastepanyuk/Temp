package day9.adapter.math.v2.calc;

import day9.adapter.math.Arifmetika;

/**
 * Created by stepanyuk on 07.08.2015.
 */
public class ArifmetikaCalculyator implements Calculator {

    @Override
    public int summa(int a, int b) {
        return Arifmetika.summa(new int[]{a,b});
    }

    @Override
    public int multiply(int a, int b) {
        return 0;
    }
}
