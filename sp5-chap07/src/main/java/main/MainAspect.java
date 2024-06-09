package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import cinfig.AppCtx;

public class MainAspect {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Calculator cal = ctx.getBean("recCalculator",Calculator.class);
		Calculator cal2 = ctx.getBean("impeCalculator",Calculator.class);
		System.out.println("결과 = " +cal.factorial(10));
		System.out.println("결과 = " +cal.factorial(10));
		System.out.println("결과 = " +cal.factorial(10));
		
		
	}
}
