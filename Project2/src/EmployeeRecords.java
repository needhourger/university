import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmployeeRecords {
	ArrayList<Employee> employeeArray = new ArrayList<Employee>();
	int index;
	public EmployeeRecords() {
		index = 0;
	}
	//asks user for student data, creates new Student object, and adds to studentArray
	public void addEmployee(String fname, String lname, String phone,String email,String address,String emContact,String age,String category,String department,String salaryType,String salary) {
//		if(index>=employeeArray.size()) {
//			System.out.println("The student records are full!");
//			return;
//		}
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(fname);
		newEmployee.setLastName(lname);
		newEmployee.setPhone(phone);
		newEmployee.setEmail(email);
		newEmployee.setAddress(address);
		newEmployee.setEmContact(emContact);
		newEmployee.setAge(Integer.parseInt(age));
		newEmployee.setCategory(category);
		newEmployee.setDepartment(department);
		newEmployee.setSalaryType(salaryType);
		newEmployee.setSalary(Integer.parseInt(salary));
		
		employeeArray.add(newEmployee);
		//employeeArray.get(index) = newEmployee;
		index++;
	}
	
	//asks user for student, moves objects to left and decrements index value
	public void removeEmployee(String fname, String lname, JTextArea textArea) {
		int i = getEmployeeIndex(fname, lname);
		if (i==-1) {
			textArea.setText("Employee is not in the records!");
			return;
		}
		employeeArray.remove(i);
		textArea.setText("Employee successfully removed.");
		index--;
//		if(i>=0) {
//			for(int pos=i;pos<index;pos++) {
//				employeeArray.set(i, employeeArray.get(i+1));
//			}
//			textArea.setText("Employee successfully removed.");
//			index--;
//		}
//		else {
//			textArea.setText("Employee is not in the records!");
//		}
	}
	
	
	//asks user for student, prints info of student
	public void retrieveEmployeeInfo(String fname, String lname, JTextArea textArea) {
		int employeeIndex = getEmployeeIndex(fname, lname);
		
		if(employeeIndex >=0) {
			employeeArray.get(employeeIndex).printInfo(employeeArray.get(employeeIndex), textArea);
		}
		else {
			textArea.setText("Employee not in the records!");
		}
	}
	
	//returns index of student using first and last name, returns -1 if not in array
	public int getEmployeeIndex(String fname, String lname) {
		for(int i=0; i<index;i++) {
			if (employeeArray.get(i).getFirstName().equals(fname) && employeeArray.get(i).getLastName().equalsIgnoreCase(lname)) {
				return i;
			}
		}
		
		return -1;
	}
	//asks user for student, then asks user to choose what data to modify, that resets data to new info
	public void modifyEmployee(String fname, String lname, JTextArea textArea, JTextField fNameTextField2, JTextField lNameTextField2, JTextField phoneTextField, JTextField emailTextField, JTextField addressTextField, JTextField emContactTextField, JTextField ageTextField, JComboBox<String> categoryComboBox, JTextField departmentTextField, JComboBox<String> salaryTypeComboBox, JTextField salaryTextField) {
		int employeeIndex = getEmployeeIndex(fname, lname);
		if(employeeIndex >=0) {
			fNameTextField2.setText(employeeArray.get(employeeIndex).getFirstName());
			lNameTextField2.setText(employeeArray.get(employeeIndex).getLastName());
			phoneTextField.setText(employeeArray.get(employeeIndex).getPhone());
			emailTextField.setText(employeeArray.get(employeeIndex).getEmail());
			addressTextField.setText(employeeArray.get(employeeIndex).getAddress());
			emContactTextField.setText(employeeArray.get(employeeIndex).getEmContact());
			ageTextField.setText(Integer.toString(employeeArray.get(employeeIndex).getAge()));
			categoryComboBox.setSelectedItem(employeeArray.get(employeeIndex).getCategory());
			departmentTextField.setText(employeeArray.get(employeeIndex).getDepartment());
			salaryTypeComboBox.setSelectedItem(employeeArray.get(employeeIndex).getSalaryType());
			salaryTextField.setText(Integer.toString(employeeArray.get(employeeIndex).getSalary()));
	
		}
		else {
			textArea.setText("Employee not in the records.");
		}
	}
	public void submitChanges(String fname, String lname, String fname2,String lname2, String phone,String email,String address,String emContact, int age, String category, String department, String salaryType, int salary) {
		int employeeIndex = getEmployeeIndex(fname,lname);
		Employee employee = new Employee(fname2, lname2, phone, email, address,emContact,age,category,department,salaryType,salary);
		employeeArray.set(employeeIndex, employee);
	}
}

