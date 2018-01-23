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

/**
 * 用户激活邮件Servlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ActiveServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受激活码
	try {
		String code = request.getParameter("code");
		UserService userService = new UserServiceImpl();
		User user = userService.findByCode(code);
		if(user != null) {
			//已经查询到该用户,修改该用户
			user.setState(1);
			user.setCode(null);
			userService.update(user);
			request.setAttribute("msg","您已经激活成功请去登录");
		}else {
			//根据激活码没有查询到该用户
			request.setAttribute("msg","您的激活码有误!请重新激活!");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据激活码查询用户
		//已经查询到,修改用户状态
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
