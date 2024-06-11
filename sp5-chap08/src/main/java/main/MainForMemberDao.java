package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.MemberDao;

public class MainForMemberDao {
	
	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); 
		
		memberDao = ctx.getBean("memberDao",MemberDao.class);
		
		selectAll();
	}
	
	public static void selectAll() {
		List<Member> members = memberDao.selectAll();
		for(Member m : members) {
			System.out.println(m.getId() + m.getEmail() + m.getName());
		}
	}
}
