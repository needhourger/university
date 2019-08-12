package sj.db.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.db.bean.Administrator;
import sj.db.dao.AdminDao;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String Admin_name = request.getParameter("Admin_name");
		String Admin_pwd = request.getParameter("Admin_pwd");
		Administrator admin = new Administrator();
		admin.setAdmin_name(Admin_name);
		admin.setAdmin_pwd(Admin_pwd);
		AdminDao dao = new AdminDao();
		if(dao.judegLogin(admin)){
			request.getRequestDispatcher("/jsps/index.jsp")
			.forward(request, response);
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			//登陆失败跳转到登陆页面，重新登陆
			request.getRequestDispatcher("/jsps/users/login.jsp")
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
