package base;
import java.util.Scanner;
import window.Window;

public class EmployeeRecords {
	public static Employee employeeArray[] = new Employee[10];
	int index;
	public EmployeeRecords() {
		index = 0;
	}
	//asks user for student data, creates new Student object, and adds to studentArray
	public void addEmployee(String[] args) {
		if(index>=employeeArray.length) {
			Window.resTextArea.append("The employee records are full!\n");
			return;
		}
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(args[0]);
		newEmployee.setLastName(args[1]);
		newEmployee.setPhone(Integer.parseInt(args[2]));
		newEmployee.setEmail(args[3]);
		newEmployee.setAddress(args[4]);
		newEmployee.setEmContact(args[5]);
		newEmployee.setAge(Integer.parseInt(args[6]));
		//Drop down menu
		newEmployee.setCategory(args[7]);
		newEmployee.setDepartment(args[8]);
		//Drop down menu?
		newEmployee.setSalaryType(args[9]);
		newEmployee.setSalary(Integer.parseInt(args[10]));
		
		employeeArray[index] = newEmployee;
		index++;
		Window.resTextArea.append("add employee record successfully!\n");
	}
	
	public void removeEmployee(String[] args) {
		String fname = args[0];
		String lname = args[1];
		int i = getEmployeeIndex(fname, lname);
		if(i==9) {
			employeeArray[9] = null;
			Window.resTextArea.append("Employee successfully removed.\n");
		}
		else {
			if(i>=0) {
				for(int pos=i;pos<index;pos++) {
					employeeArray[i] = employeeArray[i+1];
				}
				Window.resTextArea.append("Employee successfully removed.\n");
				index--;
			}
			else {
				Window.resTextArea.append("Employee is not in the records!\n");
			}
		}
	}
	
	//asks user for student, moves objects to left and decrements index value
	public void removeEmployee(Scanner in) {
		System.out.println("Enter the employee's first name: ");
		String fname = in.nextLine();
		System.out.println("Enter the employee's last name: ");
		String lname = in.nextLine();
		int i = getEmployeeIndex(fname, lname);
		if(i==9) {
			employeeArray[9] = null;
			System.out.println("Employee successfully removed.");
		}
		else {
			if(i>=0) {
				for(int pos=i;pos<index;pos++) {
					employeeArray[i] = employeeArray[i+1];
				}
				System.out.println("Employee successfully removed.");
				index--;
			}
			else {
				System.out.println("Employee is not in the records!");
			}
		}
	}
	
	
	//asks user for student, prints info of student
	public void retrieveEmployeeInfo(Scanner in) {
		System.out.println("Enter the employee's first name: ");
		String fname = in.nextLine();
		System.out.println("Enter the employee's last name: ");
		String lname = in.nextLine();
		int employeeIndex = getEmployeeIndex(fname, lname);
		
		if(employeeIndex >=0) {
			employeeArray[employeeIndex].printInfo(employeeArray[employeeIndex]);
		}
		else {
			System.out.println("Student not in the records!");
		}
	}
	
	//returns index of student using first and last name, returns -1 if not in array
	public int getEmployeeIndex(String fname, String lname) {
		for(int i=0; i<index;i++) {
			if (employeeArray[i].getFirstName().equals(fname) && employeeArray[i].getLastName().equalsIgnoreCase(lname)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void modifyEmployee(String args[]) {
		String fname = args[0];
		String lname = args[1];
		int employeeIndex = getEmployeeIndex(fname, lname);
		
		
		if (!employeeArray[employeeIndex].getFirstName().equals(args[2])) {
			employeeArray[employeeIndex].setFirstName(args[2]);
			Window.resTextArea.append("Successfully changed first name to " + employeeArray[employeeIndex].getFirstName()+"\n");
		}
		
		if (!employeeArray[employeeIndex].getLastName().equals(args[3])) {
			employeeArray[employeeIndex].setLastName(args[3]);
			Window.resTextArea.append("Successfully changed last name to " + employeeArray[employeeIndex].getLastName()+"\n");
		}
		
		int newPhone = Integer.parseInt(args[4]);
		if (employeeArray[employeeIndex].getPhone()!=newPhone) {
			employeeArray[employeeIndex].setPhone(newPhone);
			Window.resTextArea.append("Successfully changed phone to " + employeeArray[employeeIndex].getPhone()+"\n");
		}
		
		
		if (!employeeArray[employeeIndex].getEmail().equals(args[5])) {
			employeeArray[employeeIndex].setEmail(args[5]);
			Window.resTextArea.append("Successfully changed email to " + employeeArray[employeeIndex].getEmail()+"\n");
		}
		
		if (!employeeArray[employeeIndex].getAddress().equals(args[6])) {
			employeeArray[employeeIndex].setAddress(args[6]);
			Window.resTextArea.append("Successfully changed address to " + employeeArray[employeeIndex].getAddress()+"\n");
		}
		
		if (!employeeArray[employeeIndex].getEmContact().equals(args[7])) {
			employeeArray[employeeIndex].setEmContact(args[7]);
			Window.resTextArea.append("Successfully changed emergency contact to " + employeeArray[employeeIndex].getEmContact()+"\n");
		}
		
		int newAge = Integer.parseInt(args[8]);
		if (employeeArray[employeeIndex].getAge()!=newAge) {
			employeeArray[employeeIndex].setAge(newAge);
			Window.resTextArea.append("Successfully changed age to " + employeeArray[employeeIndex].getAge()+"\n");
		}
		
		
		if (!employeeArray[employeeIndex].getCategory().equals(args[9])) {
			employeeArray[employeeIndex].setCategory(args[9]);
			Window.resTextArea.append("Successfully changed category to " + employeeArray[employeeIndex].getCategory()+"\n");
		}
		
		if (!employeeArray[employeeIndex].getDepartment().equals(args[10])) {
			employeeArray[employeeIndex].setDepartment(args[10]);
			Window.resTextArea.append("Successfully changed department to " + employeeArray[employeeIndex].getDepartment()+"\n");
		}
		
		if (!employeeArray[employeeIndex].getSalaryType().equals(args[11])) {
			employeeArray[employeeIndex].setSalaryType(args[11]);
			Window.resTextArea.append("Successfully changed salary type to " + employeeArray[employeeIndex].getSalaryType()+"\n");
		}
		
		int newSalary = Integer.parseInt(args[12]);
		if (employeeArray[employeeIndex].getSalary()!=newSalary) {
			employeeArray[employeeIndex].setSalary(newSalary);
			Window.resTextArea.append("Successfully changed salary to " + employeeArray[employeeIndex].getSalary()+"\n");
		}
	}
	
	//asks user for student, then asks user to choose what data to modify, that resets data to new info
	public void modifyEmployee(Scanner in) {
		System.out.println("Enter the employee's first name: ");
		String fname = in.nextLine();
		System.out.println("Enter the employee's last name: ");
		String lname = in.nextLine();
		int employeeIndex = getEmployeeIndex(fname, lname);
		
		System.out.println("Modification Choices: ");
		System.out.printf("1- First name \n2- Last name \n3- Phone \n4- Email \n5- Address \n6- Emergency Contact \n7- Age \n8- Category \n9- Department\n10- Salary Type\n11- Salary\n12- Go back.\n");
		System.out.println("Enter your choice for modification(1-10): ");
		int modificationChoice = Integer.parseInt(in.nextLine());
		
		switch(modificationChoice) {
		case 1:
			System.out.println("New First Name: ");
			String newFname = in.nextLine();
			employeeArray[employeeIndex].setFirstName(newFname);
			System.out.println("Successfully changed first name to " + employeeArray[employeeIndex].getFirstName());
			break;
		case 2:
			System.out.println("New Last Name: ");
			String newLname = in.nextLine();
			employeeArray[employeeIndex].setLastName(newLname);
			System.out.println("Successfully changed last name to " + employeeArray[employeeIndex].getLastName());
			break;
		case 3:
			System.out.println("New Phone: ");
			int newPhone = Integer.parseInt(in.nextLine());
			employeeArray[employeeIndex].setPhone(newPhone);
			System.out.println("Successfully changed phone to " + employeeArray[employeeIndex].getPhone());
			break;
		case 4:
			System.out.println("New Email: ");
			String newEmail = in.nextLine();
			employeeArray[employeeIndex].setEmail(newEmail);
			System.out.println("Successfully changed email to " + employeeArray[employeeIndex].getEmail());
			break;
		case 5:
			System.out.println("New Address: ");
			String newAddress = in.nextLine();
			employeeArray[employeeIndex].setAddress(newAddress);
			System.out.println("Successfully changed address to " + employeeArray[employeeIndex].getAddress());
			break;
		case 6:
			System.out.println("New Emergency Contact: ");
			String newEContact = in.nextLine();
			employeeArray[employeeIndex].setEmContact(newEContact);
			System.out.println("Successfully changed emergency contact to " + employeeArray[employeeIndex].getEmContact());
			break;
		case 7:
			System.out.println("New Age: ");
			int newAge = Integer.parseInt(in.nextLine());
			employeeArray[employeeIndex].setAge(newAge);
			System.out.println("Successfully changed age to " + employeeArray[employeeIndex].getAge());
			break;
		case 8:
			System.out.println("New Category: ");
			String newCategory = in.nextLine();
			employeeArray[employeeIndex].setCategory(newCategory);
			System.out.println("Successfully changed category to " + employeeArray[employeeIndex].getCategory());
			break;
		case 9:
			System.out.println("New Department: ");
			String newDepartment = in.nextLine();
			employeeArray[employeeIndex].setDepartment(newDepartment);
			System.out.println("Successfully changed department to " + employeeArray[employeeIndex].getDepartment());
			break;
		case 10:
			System.out.println("New Salary Type: ");
			String newSalaryType = in.nextLine();
			employeeArray[employeeIndex].setSalaryType(newSalaryType);
			System.out.println("Successfully changed salary type to " + employeeArray[employeeIndex].getSalaryType());
			break;
		case 11:
			System.out.println("New Salary: ");
			int newSalary = Integer.parseInt(in.nextLine());
			employeeArray[employeeIndex].setSalary(newSalary);
			System.out.println("Successfully changed salary to " + employeeArray[employeeIndex].getSalary());
			break;
		case 12:
			System.out.println("No modification made.");
			break;
		default:
			System.out.println("ERROR: WRONG CHOICE");
		}
	}
}
