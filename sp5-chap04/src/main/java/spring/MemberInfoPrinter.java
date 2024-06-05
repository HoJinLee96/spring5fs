package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {

	@Autowired
	MemberDao memberDao;
	@Autowired
	MemberPrinter memberPrinter;

	public void print(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException("회원 정보 없음.");
//		System.out.println(member.toString()); MemberPrinter 클래스 없었을 때 사용
		memberPrinter.print(member);
		
	}
}
