package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForCPS {
	
	private static AnnotationConfigApplicationContext ctx;
	
	public static void main(String[] args) {
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		changePasswordService("abc@abc.com","1234","5678");
		
	}
	
	public static void changePasswordService(String email, String oldPassword, String newPassword) {
		ChangePasswordService service = ctx.getBean("changePasswordService",ChangePasswordService.class);
		
		try{
			service.changePassword(email, oldPassword, newPassword);
			System.out.println("암호 변경 완료");
		}catch(MemberNotFoundException e) {
			System.out.println("회원 데이터 없음.");
		}catch (WrongIdPasswordException e) {
			System.out.println("암호 다름.");
		}
		
	}
}
