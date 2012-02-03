package example.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MemberDao {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    JdbcTemplate jdbcTemplate;

    public void setupIfNotYet() {
        try {
            jdbcTemplate.queryForInt("select count(1) from member");
        } catch (Exception e) {
            log.info("Create table 'member' and insert some records...");
            jdbcTemplate.execute("create table member (id int primary key, name varchar(100))");
            jdbcTemplate.update("insert into member values(?,?)", 1, "Andy");
            jdbcTemplate.update("insert into member values(?,?)", 2, "Brian");
            jdbcTemplate.update("insert into member values(?,?)", 3, "Charles");
        }
    }

    public List<Member> findAll() {
        setupIfNotYet();
        return jdbcTemplate.query("select * from member", mapper);
    }

    RowMapper<Member> mapper = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet resultSet, int i) throws SQLException {
            return extractor.extractData(resultSet);
        }
    };

    ResultSetExtractor<Member> extractor = new ResultSetExtractor<Member>() {
        @Override
        public Member extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Member member = new Member();
            member.setId(resultSet.getInt("id"));
            member.setName(resultSet.getString("name"));
            return member;
        }
    };

}
