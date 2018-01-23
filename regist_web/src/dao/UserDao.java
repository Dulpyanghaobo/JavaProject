package dao;

import java.sql.SQLException;

import domain.User;

public interface UserDao {
void regist(User user) throws SQLException ;

User findByCode(String code) throws SQLException ;

void update(User user) throws Exception;
}
