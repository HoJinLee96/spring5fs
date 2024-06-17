package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {
	
	@Autowired
	private MemberDao memDao;
	
	@Autowired
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member==null) {
			System.out.println("회원 가입 되어있지 않습니다. \n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	public void setmemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer ;
	}
}
