package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@Import(AppConf2.class)
public class AppConfImport {
	
	@Bean
	MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
