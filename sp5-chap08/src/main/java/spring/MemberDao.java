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

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
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

		List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL=?", (ResultSet rs, int rowNum) -> {
			Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
			member.setId(rs.getLong("ID"));
			return member;
		}, email);

		return results.isEmpty() ? null : results.get(0);

	}

	public void insert(Member member) {
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement pstmt = con.prepareStatement(
//						"insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)");
//				pstmt.setString(1, member.getEmail());
//				pstmt.setString(2, member.getPassword());
//				pstmt.setString(3, member.getName());
//				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
//				return pstmt;
//			}
//		});
		jdbcTemplate.update("insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)",member.getEmail(),member.getPassword(),member.getName(),member.getRegisterDateTime());
	}

	public void update(Member member) {
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
