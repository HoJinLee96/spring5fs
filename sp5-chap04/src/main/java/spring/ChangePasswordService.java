package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	@Autowired
	MemberDao memberDao;

	public void changePssword(String email, String oldPassword, String newPassword) {
		Member member = memberDao.selectByEmail(email);
		if(member==null)
			throw new MemberNotFoundException("회원 정보 없음.");
		try {
		member.changePassword(oldPassword, newPassword);
		memberDao.update(member);
		}
		catch(WrongIdPasswordException e) {
			System.out.println(e.getMessage());
		}
	}
}
