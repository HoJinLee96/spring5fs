package spring;

public class ChangePasswordService {
	private MemberDao memberDao;

	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberDao.update(member);
	}

	//세터로 memberDao 설정
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
}
