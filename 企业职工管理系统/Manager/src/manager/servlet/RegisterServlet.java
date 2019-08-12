package manager.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userservice = new UserService();
		/**
		 * 封装表当数据
		 */
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
	
		//	User form = new User();
		/**
		 *  添加新任务（表单校验）
		 *  1、创建一个Map，用来装载所有的表单错误信息
		 *     在校验过程中，如果失败，向map添加错误信息，其中key为表单字段名称
		 *  2、在校验之后，查看map长度是否大于0，如果大于0，说明有错误，
		 *      > 保存map到request中，保存form到request中，转发到regist.jsp
		 *  3、如果map为空，说明没有错误信息，向下执行
		 */
		//用来装载所有用户信息
		Map<String,String> errors = new HashMap<String,String>();
		//对用户名进行校验
		String username = form.getusername();
		
		//String username = request.getParameter("UserName");
		if(username==null||username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
			
		}else if(username.length()<3||username.length()>15){
			errors.put("username", "用户名长度必须在3~15之间!");
		}
		//对密码进行校验
		String password = form.getpassword();
		//String password = request.getParameter("Password");
		if(password==null||password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		}else if(password.length()<3||password.length()>15){
			errors.put("password", "密码长度必须在3~15之间!");
		}
		
	
		//对验证码进行校验
	/*	String sessionVerifyCode = (String) request.getSession().getAttribute("check_code");
		String verifyCode = form.getVerifyCode();
		if(verifyCode==null||verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		}else if(verifyCode.length()!=4){
			errors.put("verifyCode", "验证码长度必须为4!");
		}else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)){
			errors.put("verifyCode", "验证码错误！");
		}*/
	   // form.setUserName(username);
	   // form.setPassword(password);
	  
		if(errors!=null&&errors.size()>0) {
			/**
			 * 1、保存errors到request域中
			 * 2、保存form到request域中，为了回显
			 * 3、转发到regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		    return;
		}
		
		try {
			userservice.regist(form);
		//	response.getWriter().print(request.getContextPath()+"*******");
		//	System.out.println(request.getContextPath()+"*******");
			response.getWriter().print("<h1>注册成功！</h1><a href='"+request.getContextPath()+"/login.jsp'"+">点击这里</a>");
		    
		} catch (UserException e) {
		    request.setAttribute("msg", e.getMessage());
		    request.setAttribute("user", form);
		    request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
	}
    /**
     * 判断Map是否为空，不为空，说明存在错误
     */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
