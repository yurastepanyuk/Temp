package day9.adapter.math.v1.calc;

import day9.adapter.math.Arifmetika;

public class Calculator {

	Arifmetika arifmetika;

	public Calculator() {
		arifmetika = new Arifmetika();
	}

	public int summa(int a, int b) {
		return arifmetika.summa(new int[]{a,b});
		//return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}
}
