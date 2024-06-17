package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;
import spring.WrongIdPasswordException;


public class Main {
	private static ApplicationContext ctx = null;
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			//new로 시작하고 길이 확인
			if (command.trim().startsWith("new ") && command.trim().split(" ").length ==5) {
				processNewCommand(command.split(" "));
				continue;
			} 
			//change로 시작하고 길이 확인
			else if (command.trim().startsWith("change ") && command.trim().split(" ").length ==4) {
				processChangeCommand(command.split(" "));
				continue;
			}
			else if(command.trim().equals("list")){
				processListCommand();
				continue;
			}
			else if(command.trim().startsWith("info ")&& command.trim().split(" ").length ==2){
				processInfoCommand(command.split(" "));
				continue;
			}
			else if(command.trim().equals("version")) {
				processVersionCommand();
				continue;
			}
//			위 조건이 다 아닐 경우 도움말 while문이라 이런 방식으로 작성
			printHelp();
		}
	}
	private static void processNewCommand(String[] arg) {
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
		//왜 인자통한 생성자를 안만들었을까? 잘모르겠음
		RegisterRequest req = new RegisterRequest(); 
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호를 확인해 주세요.\n");
			return;
		} 
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호를 확인해 주세요.\n");
			return;
		}
		long reqId = 0;
		try {
			reqId = regSvc.regist(req);
			System.out.println("성공적으로 등록된 당신 아이디 : " + reqId  +"\n");
		}catch(DuplicateMemberException e) {
			System.out.println("이미 가입되어 있는 아이디 입니다.\n");
		}
	}
	
	private static void processChangeCommand(String[] arg) {
		ChangePasswordService pwdSvc = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		try {
			pwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("비밀번호 변경 완료.\n");
		} catch (MemberNotFoundException | WrongIdPasswordException e) {
			System.out.println("회원정보 확인해 주세요.\n");
		}
		
	}
	
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private static void processInfoCommand(String[] arg) {
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter",VersionPrinter.class);
		versionPrinter.print();
	}
	
	static public void printHelp() {
		System.out.println();
		System.out.println("잘못된 입력 입니다.");
		System.out.println("사용법 예시");
		System.out.println("회원 가입 : new 이메일 이름 암호 암호확인");
		System.out.println("비밀번호 변경 : change 이메일 현재비번 변경비번");
		System.out.println("종료 : exit");
		System.out.println();
	}
	
}
