package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.MemberRegisterService;
import spring.RegisterRequest;

public class MainForSpring {

	private static AnnotationConfigApplicationContext ctx;
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
			// new로 시작하고 길이 확인
			if (command.trim().startsWith("new ") && command.trim().split(" ").length == 5) {
				processNewCommand(command.split(" "));
				continue;
			}
			// change로 시작하고 길이 확인
//			else if (command.trim().startsWith("change ") && command.trim().split(" ").length == 4) {
//				processChangeCommand(command.split(" "));
//				continue;
//			} else if (command.trim().equals("list")) {
//				processListCommand();
//				continue;
//			} else if (command.trim().startsWith("info ") && command.trim().split(" ").length == 2) {
//				processInfoCommand(command.split(" "));
//				continue;
//			} else if (command.trim().equals("version")) {
//				processVersionCommand();
//				continue;
//			}
//				위 조건이 다 아닐 경우 도움말 while문이라 이런 방식으로 작성
			printHelp();
		}
	}

	public static void processNewCommand(String args[]) {
		MemberRegisterService mrs = ctx.getBean(MemberRegisterService.class);
		RegisterRequest request = new RegisterRequest(args[1],args[2],args[3],args[4]);
		if(!request.passwordToConfirmPassword()) {
			printHelp();
			return;
		}
		try {
			long id = mrs.regist(request);
			System.out.println("당신의 아이디는 : "+id);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
//		System.out.println(mrs.regist(request));
	}
	
	static public void printHelp() {
		System.out.println();
		System.out.println("잘못된 입력 입니다.");
		System.out.println("사용법 예시");
		System.out.println("회원 가입 : new 이메일 이름 암호 암호확인");
		System.out.println("비밀번호 변경 : change 이메일 현재비번 변경비번");
		System.out.println("모든 회원 조회 : list");
		System.out.println("회원 정보 조회 : info 이메일");
		System.out.println("버전 : version");
		System.out.println("종료 : exit");
		System.out.println();
	}
}
