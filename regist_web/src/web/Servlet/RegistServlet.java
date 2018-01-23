package web.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;
import service.UserServiceImpl;
import utils.UUIDUtils;

/**
 * 用户注册的servlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收数据:
		//处理中文乱码
	try{
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		//封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setState(0);//0表示未激活1表示激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		//调整业务层处理数据
		UserService userService = new UserServiceImpl();
		userService.regist(user);
		//页面跳转
		request.setAttribute("msg","您已经注册成功!请去邮箱激活!");
		request.getRequestDispatcher("/msg.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("连接数据库失败",e);
		}
		//页面跳转
 catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
