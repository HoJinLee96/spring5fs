package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.DuplicateMemberException;
import spring.Member;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.RegisterRequest;

public class MainForMemberDao {
	
	private static MemberDao memberDao;
	private static AnnotationConfigApplicationContext ctx;
	public static void main(String[] args) throws DuplicateMemberException {
		
		 ctx = new AnnotationConfigApplicationContext(AppCtx.class); 
		memberDao = ctx.getBean("memberDao",MemberDao.class);
		
		RegisterRequest request = new RegisterRequest("st2035@naver.com","123","12","lee");
		try {
		insert(request);
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
		selectByEmail("st2035@naver.com");
		selectAll();
		count();
	}
	
	public static void selectAll() {
		List<Member> members = memberDao.selectAll();
		for(Member m : members) {
			System.out.println("아이디 : " + m.getId() + " 이메일 : " + m.getEmail() + " 이름 : " +  m.getName());
		}
	}
	public static void selectByEmail(String email) {
		Member m = memberDao.selectByEmail(email);
		System.out.println("아이디 : " + m.getId() + " 이메일 : " + m.getEmail() + " 이름 : " +  m.getName() + " 비밀번호 : " + m.getPassword());
	}
	
	public static void insert(RegisterRequest request) {
		MemberRegisterService service = ctx.getBean("memberRegisterService",MemberRegisterService.class);
		if(!request.isPasswordEqualToConfirmPassword()) {
			throw new RuntimeException("비번 틀리는 레전드 상황 발생~");
		}else {
			service.regist(request);
		}
	}
	
	public static void count() {
		System.out.println("회원 수 "+memberDao.count()+"명");
	}
}
