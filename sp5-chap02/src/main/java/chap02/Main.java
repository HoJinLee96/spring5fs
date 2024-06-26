package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		//AnnotationConfigApplicationContext 클래스는 자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리한다.
		//파라미터로 제공한 AppContext.class에 정의한 @Bean 설정을 읽고 객체 생성 및 초기화한다.
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		
		//getBean()은 메서드 명 "greeter" 와 객체 타입 Greeter.class를 검색한다.  
		Greeter g = ctx.getBean("greeter",Greeter.class);
		
		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
	}

}
