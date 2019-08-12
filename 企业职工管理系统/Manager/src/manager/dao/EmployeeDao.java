package manager.dao;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import manager.domian.Employee;


public class EmployeeDao implements EmployeeDaoImpl{
	private QueryRunner qr = new TxQueryRunner();
	  
	   public void add(Employee e)  {
		  
	       try {
	    	   String sql = "insert into employee values(?,?,?,?,?,?,?,?,"
	    	   		+ "?,?,?,?,?,?,?,?,"
	    	   		+ "?,?,?,?,?,?,?,?,?,?,?)";
	    	   Object[] params = {e.getEmployeeID(),e.getUserName(),e.getSex(),
	    			   e.getBranch(),e.getBirthday(),e.getNativePlace(),e.getMarriage(),
	    			   e.getIdentifyID(),e.getPolitics(),e.getFolk(),e.getEducation(),
	    			   e.getDepartment(),e.getGraduateDate(),e.getUniversity(),e.getAccumulateID(),
	    			   e.getAdminstrationLevel(),e.getDuty(),e.getPosition(),e.getForeignLanuage(),
	    			   e.getfLevel(),e.getComputerLevel(),e.getPhone(),e.getMobilePhone(),e.getAddress(),
	    			   e.getIncumbency(),e.getIncumbencyType(),e.getResume()};
	    	   qr.update(sql, params);  
		} catch (SQLException el) {
			el.printStackTrace();
			throw new RuntimeException();
		}
	       
	   }
	@Override
	public void delete(Employee e) {
		String sql ="delete from Employee where ";
	}
	@Override
	public void alter(Employee e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Employee select(String EmployeeID)  {
		// TODO Auto-generated method stub
		Employee em = null;
		try {
		   
			String sql="select * from Employee where EmployeeID=?";
		    Object param = EmployeeID;
		    em=qr.query(sql,new BeanHandler<Employee>(Employee.class), param);
		    return em;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
