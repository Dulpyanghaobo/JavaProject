package service;



import java.sql.SQLException;

import domain.User;

public interface UserService {
void regist(User user) throws Exception ;

User findByCode(String code) throws SQLException, Exception;

void update(User user) throws Exception ;
}
