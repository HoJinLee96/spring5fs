package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"aspect","chap07"})
public class AppCtx {
//
//	@Bean
//	public Calculator calculator() {
//		return new RecCalculator();
//	}
}
