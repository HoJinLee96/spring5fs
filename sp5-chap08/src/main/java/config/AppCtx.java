package config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;

@Configuration
public class AppCtx {

	@Bean(destroyMethod="close") // close = 커넥션 풀에 보관된 Connection을 닫는다.
	public DataSource dataSource() {
		DataSource ds = new DataSource(); 
		ds.setDriverClassName("com.mysql.jdbc.Driver"); //JDBC 드라이버 클래스를 MySQL 드라이버 클래스로 지정.
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8"); //JDBC URL 지정 //?useSSL=no
		ds.setUsername("spring5");
		ds.setPassword("00000000");
		ds.setInitialSize(10);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(1000*60*3);
		ds.setTimeBetweenEvictionRunsMillis(1000*10);
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
}
