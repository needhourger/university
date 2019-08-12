package manager.service;

import manager.dao.EmployeeDao;
import manager.domian.Employee;

public class EmployeeService {
    private EmployeeDao EmplDao = new EmployeeDao();
    public void addMessage(Employee employee) throws UserException {
    	Employee e = EmplDao.select(employee.getEmployeeID());
    	if(e!=null) {
   		 throw new UserException("用户名"+employee.getEmployeeID()+"已添加");
   	    }
    	EmplDao.add(employee);
    	
    }
    public Employee modifyMessage(String eid) throws UserException {
    	Employee e = EmplDao.select(eid);
    	if(e==null) {
      		 throw new UserException("用户名"+eid+"不存在");
      	 }
    	return e;
    }
}
