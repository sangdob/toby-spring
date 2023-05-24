package toby.spring.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import toby.spring.jdbc.dto.User;

import javax.sql.DataSource;
import java.util.List;


@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User save(User user) {
        String sql = "insert into user(name, age) values (?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge());
        return user;
    }

    public List<User> findByName(String name) {
        String sql = "select * from user where name = ?";
        return jdbcTemplate.query(sql, userRowMapper(), name);
    }

    public void delete(User user) {
        String sql = "delete table user where id = ?";
        jdbcTemplate.query(sql, userRowMapper(), user.getId());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) ->
                User.builder().id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
    }
}
