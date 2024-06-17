package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
//		* query()의 인자 RowMapper<>를 임의 클래스로 작성한 예제
//		List<Member> results = jdbcTemplate.query(
//			"select * from MEMBER where EMAIL = ?",
//			new RowMapper<Member>() {
//				@Override
//				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//					Member member = new Member(
//							rs.getString("EMAIL"),
//							rs.getString("PASSWORD"),
//							rs.getString("NAME"),
//							rs.getTimestamp("REGDATE").toLocalDateTime()
//							);
//					member.setId(rs.getLong("ID"));
//					return member;
//				}
//			},email
//				);
		
//		* query()의 인자 RowMapper<>를 람다식으로 작성한 예제
		List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL=?", (ResultSet rs, int rowNum) -> {
			Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
			member.setId(rs.getLong("ID"));
			return member;
		}, email);

//		임의 클래스든 람다식이든 리턴은 결국 길이가 0인 리스트인지 체크 후 알맞게 반환
		return results.isEmpty() ? null : results.get(0);

	}

	public void insert(Member member) {
//		sql파라미터 설정을 PreparedStatementCreator 통한 사용 예제
//		인자가 Object... args인 update() 문으로도 작성 가능하지만 PreparedStatementCreator 사용하는
//		이유는 아래와 같이 멤버 데이터가 하나가 아닌 여러개의 데이터를 넣거나 그 외 복잡한 파라미터를 설정할때 용이함.
	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)",
						new String[] {"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		},keyHolder);
		member.setId(keyHolder.getKey().longValue());
	}

	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME =?, PASSWORD =? where EMAIL =?",
				member.getName(),
				member.getPassword(),
				member.getEmail());
	}

	// 맴버 모음
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER", (ResultSet rs, int rowNum) -> {
			Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
			member.setId(rs.getLong("ID"));
			return member;
		});
		return results;
	}
	
	public int count() {
		
//		List<Integer> results = jdbcTemplate.query("select count(*) from MEMBER",
//				(ResultSet rs, int rowNum)->{return rs.getInt(1);}
//				);
//		return results.get(0);
		
		//위 query() 문을 queryForObject()로 대체
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
		return count;
	}
	
}
