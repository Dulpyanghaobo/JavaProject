package service;


import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.User;
import utils.MailUtils;

public class UserServiceImpl implements UserService{

	@Override
	//业务层实现用户注册的方法
	public void regist(User user) throws Exception {
		//将数据存入到数据库
		UserDao userDao = new UserDaoImpl();
		userDao.regist(user);
		//同时发送一封邮件
		MailUtils.sendMail(user.getEmail(),user.getCode());
	}

	@Override
	//根据激活码查询用户的方法
	public User findByCode(String code) throws SQLException{
		UserDao userDao = new UserDaoImpl();
		
			return userDao.findByCode(code);
		
	}

	@Override
	//业务层修改业务的方法
	public void update(User user) throws Exception {
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}

}
