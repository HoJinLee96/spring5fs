package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.Member;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppCtx {

	@Bean
	public Member member() {
		return new Member();
	}

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService();
	}

	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		return new MemberInfoPrinter();
	}

	@Bean
	public VersionPrinter versionPrinter() {
		return new VersionPrinter(1, 5);
	}

}
