package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteUserByName(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public boolean isUserNotExist(String name) {
        String realSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(realSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public boolean isExist(long id) {
        String realSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(realSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(long id, String name) {
        String sql = "UPDATE user SET name = ? WHERE id =?";
        jdbcTemplate.update(sql, id, name);
    }

    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO user (name,age) VALUES (?,?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> findByAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }
}


