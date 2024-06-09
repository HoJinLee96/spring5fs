package main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

public class MainProxy {
	public static void main(String[] args) {
		ExeTimeCalculator e1 = new ExeTimeCalculator(new ImpeCalculator());
		e1.factorial(20);
		
		ExeTimeCalculator e2 = new ExeTimeCalculator(new RecCalculator());
		e2.factorial(20);
	}
}
