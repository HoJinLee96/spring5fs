package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
public class AppConf1 {
	
	@Bean
	MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
