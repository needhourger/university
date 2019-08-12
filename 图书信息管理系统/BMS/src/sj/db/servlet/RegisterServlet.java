package sj.db.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.db.bean.Administrator;
import sj.db.dao.AdminDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/AddUserServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String Admin_name = request.getParameter("Admin_name");
		String Admin_pwd = request.getParameter("Admin_pwd");
		String Admin_old_code = request.getParameter("Admin_old_code");
		
		Administrator admin = new Administrator();
		admin.setAdmin_name(Admin_name);
		admin.setAdmin_pwd(Admin_pwd);
		//×¢²áÊ±ËùÓÃÑûÇëÂë
		admin.setAdmin_old_code(Admin_old_code);
		AdminDao dao = new AdminDao();
		if(dao.insert(admin)) {
			out.print("×¢²á³É¹¦");
		} else {
			out.print("×¢²áÊ§°Ü");
		}
	}

}
