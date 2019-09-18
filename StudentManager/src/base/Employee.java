package base;

public class Employee extends Person{
	private String category;
	private String department;
	private String salaryType;
	private int salary;
	
	public Employee() {
		super();
	}
	
	//parameterized constructor
	public Employee(String fname, String lname, int phone, String email, String address, String emContact, int age, String category, String department, String salaryType, int salary) {
		super(fname, lname, phone, email, address, emContact, age);
		this.category = category;
		this.department = department;
		this.salaryType = salaryType;
		this.salary = salary;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSalaryType() {
		return salaryType;
	}
	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	//prints all info of employee object
	public void printInfo(Employee employee) {
		System.out.printf("First name: " + employee.getFirstName()+"\nLast name: "+ employee.getLastName()+ "\nPhone: "+employee.getPhone()+"\nEmail: "+ employee.getEmail()+ '\n');
		System.out.printf("Address: " + employee.getAddress()+ "\nEmergency Contact: "+ employee.getEmContact()+"\nAge: "+ employee.getAge()+"\nCategory: " + employee.getCategory());
		System.out.printf("\nDepartment: "+ employee.getDepartment() + "\nSalary Type: "+ employee.getSalaryType() +"\nSalary: " + employee.getSalary() + "\n");
	}
}
