package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//해당 클래스를 스프링 설정 클래스로 지정
@Configuration
public class AppContext {

	//해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록
	@Bean
	public Greeter greeter() {
		//@Bean 애노테이션을 붙인 메서드는 객체를 생성하고 알맞게 초기화해야 한다.
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요!");
		return g;
	}
}
