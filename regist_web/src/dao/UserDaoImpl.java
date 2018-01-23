package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.User;
import utils.JDBCUtils;

public class UserDaoImpl implements UserDao{

	@Override
	//DAO保存用户方法
	public void regist(User user) throws SQLException {
		QueryRunner queryRunner = null;
		try {
			queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "insert into user values (?,?,?,?,?,?,?)";
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getNickname()
				,user.getState(),user.getCode()};
	queryRunner.update(sql, params);
	}

	@Override
	public User findByCode(String code) throws SQLException {
		QueryRunner queryRunner =null;
		try {
			queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String sql ="select * from user where code = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class),code);
		return null;
	}

	@Override
	//DAO中修改用户的方法
	public void update(User user) throws Exception{
	QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
	String sql ="update user set username=?,nickname=?,email=?,state=?,code=? where uid = ?";
	Object[] params = {user.getUsername(),user.getPassword(),user.getNickname()
			,user.getState(),user.getCode(),user.getUid()};
	queryRunner.update(sql,params);
	}

}
