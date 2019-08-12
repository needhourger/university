package manager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.domian.Employee;
import manager.service.EmployeeService;
import manager.service.UserException;

/**
 * Servlet implementation class ModifyEmployeeMessage
 */
@WebServlet("/ModifyEmployeeMessage")
public class ModifyEmployeeMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeID = request.getParameter("EmployeeID");
		EmployeeService es = new EmployeeService();
		Employee employee = null;
		try {
			employee=es.modifyMessage(employeeID);
		    
			request.setAttribute("Employee", employee);
			request.getRequestDispatcher("/MEM.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
