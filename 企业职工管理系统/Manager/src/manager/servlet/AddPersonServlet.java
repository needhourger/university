package manager.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.commons.CommonUtils;
import manager.domian.Employee;
import manager.service.EmployeeService;
import manager.service.UserException;
import manager.service.UserService;


/**
 * Servlet implementation class AddPersonServlet
 */
@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	//	Employee employee = new Employee();
		EmployeeService es = new EmployeeService();
		
	//	Employee employee = CommonUtils.toBean(request.getParameterMap(),Employee.class);
//		Map<String,Object> map = new HashMap<String,Object>();
//		
//	    map.put("EmployeeID", request.getParameter("EmployeeID"));
//	    map.put("UserName", request.getParameter("UserName"));
//	    map.put("Sex", request.getParameter("Sex"));
//	    map.put("Branch", request.getParameter("Branch"));
//	    map.put("Birthday", request.getParameter("Birthday"));
//	    map.put("NativePlace", request.getParameter("NativePlace"));
//	    map.put("Marriage", request.getParameter("Marriage"));
//	    map.put("IdentifyID", request.getParameter("IdentifyID"));
//	    map.put("Politics", request.getParameter("Politics"));
//	    map.put("Folk", request.getParameter("Folk"));
//	    map.put("Education", request.getParameter("Education"));
//	    map.put("Department", request.getParameter("Department"));
//	    map.put("GraduateDate", request.getParameter("GraduateDate"));
//	    map.put("University", request.getParameter("University"));
//	    map.put("AccumulateID", request.getParameter("AccumulateID"));
//	    map.put("AdminstrationLevel", request.getParameter("AdminstrationLevel"));
//	    map.put("Duty", request.getParameter("Duty"));
//	    map.put("Position", request.getParameter("Position"));
//	    map.put("ForeignLanuage", request.getParameter("ForeignLanuage"));
//	    map.put("ForeignLanuage", request.getParameter("ForeignLanuage"));
//	    map.put("ComputerLevel", request.getParameter("ComputerLevel"));
//	    map.put("Phone", request.getParameter("Phone"));
//	    map.put("MobilePhone", request.getParameter("MobilePhone"));
//	    map.put("Address", request.getParameter("Address"));
//	    map.put("Incumbency", request.getParameter("Incumbency"));
//	    map.put("Incumbency", request.getParameter("Incumbency"));
//	    map.put("Resume", request.getParameter("Resume"));
	    
		Employee employee = CommonUtils.toBean(request.getParameterMap(),Employee.class );
	    System.out.println(employee.getGraduateDate());
	    System.out.println(employee.getComputerLevel());
	    System.out.println(employee.getFolk());
		try {
			es.addMessage(employee);
			System.out.println("添加成功");
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
