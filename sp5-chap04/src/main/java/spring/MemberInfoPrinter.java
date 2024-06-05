package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {

	@Autowired
	MemberDao memberDao;

	public void print(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException("회원 정보 없음.");
		System.out.println(member.toString());
	}
}
