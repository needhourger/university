package manager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import manager.domian.User;
import manager.service.UserException;
import manager.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userse = new UserService();
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		//String username = request.getParameter("UserName");
		//String password = request.getParameter("Password");
		// form.setUserName(username);
		// form.setPassword(password);
		try {
			User user = userse.login(form);
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect(request.getContextPath()+"/Welcome.jsp");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}