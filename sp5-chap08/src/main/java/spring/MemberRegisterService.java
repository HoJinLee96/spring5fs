package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("memberRegSvc")
public class MemberRegisterService {
	
	@Autowired
	private MemberDao memberDao;

	//생성자로 memberDao 설정
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
	

}
