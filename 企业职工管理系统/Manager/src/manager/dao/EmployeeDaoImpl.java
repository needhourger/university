package manager.dao;

import manager.domian.Employee;

public interface EmployeeDaoImpl {
	public void add(Employee e);
	public void delete(Employee e);
	public void alter(Employee e);
	public Employee select(String eID);
}
