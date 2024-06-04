package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {

	@Autowired
	MemberDao memberDao;

	public MemberRegisterService() {
		super();
	}

	public long regist(RegisterRequest req) {

		Member member = memberDao.confirmEmail(req.getEmail());
		if (member != null) {
			throw new RuntimeException("이미 가입된 회원정보 있음");
		}
		Member newmember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newmember);

		return newmember.getId();
	}

}
