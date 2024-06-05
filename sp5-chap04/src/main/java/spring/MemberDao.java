package spring;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberDao {
	
	@Autowired
	Member member;
	
	HashMap<String,Member> map = new HashMap();
	long id = 0;

	public MemberDao() {
		super();
	}

	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public long insert(Member member) {
		member.setId(++id);
		map.put(member.getEmail(), member);
		return member.getId();
	}
	
	public Collection<Member> selectAll() {
		return map.values();
	}

	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
}
