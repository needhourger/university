package base;
import java.util.Scanner;

import window.Window;


public class StudentRecords {
	public static Student studentArray[] = new Student[10];
	int index;
	public StudentRecords() {
		index = 0;
	}
	//asks user for student data, creates new Student object, and adds to studentArray
	public void addStudent(String[] args) {
		if(index>=studentArray.length) {
			Window.resTextArea.append("The student records are full!\n");
			return;
		}
		Student newStudent = new Student();
		newStudent.setFirstName(args[0]);
		newStudent.setLastName(args[1]);
		newStudent.setPhone(Integer.parseInt((args[2])));
		newStudent.setEmail(args[3]);
		newStudent.setAddress(args[4]);
		newStudent.setEmContact(args[5]);
		newStudent.setAge(Integer.parseInt(args[6]));
		newStudent.setClassLevel(args[7]);
		newStudent.setMajor(args[8]);
		newStudent.setGpa(Float.parseFloat(args[9]));
		
		
		studentArray[index] = newStudent;
		index++;
		Window.resTextArea.append("Add student record successfully.\n");
	}
	
	public void removeStudent(String[] args) {
		String fname = args[0];
		String lname = args[1];

		int i = getStudentIndex(fname, lname);
		if(i==9) {
			studentArray[9] = null;
			Window.resTextArea.append("Student successfully removed.\n");
		}
		else {
			if(i>=0) {
				for(int pos=i;pos<index;pos++) {
					studentArray[i] = studentArray[i+1];
				}
				Window.resTextArea.append("Student successfully removed.\n");
				index--;
			}
			else {
				Window.resTextArea.append("Student is not in the records!\n");
			}
		}
	}
	
	//asks user for student, moves objects to left and decrements index value
	public void removeStudent(Scanner in) {
		System.out.println("Enter the student's first name: ");
		String fname = in.nextLine();
		System.out.println("Enter the student's last name: ");
		String lname = in.nextLine();

		int i = getStudentIndex(fname, lname);
		if(i==9) {
			studentArray[9] = null;
			System.out.println("Student successfully removed.");
		}
		else {
			if(i>=0) {
				for(int pos=i;pos<index;pos++) {
					studentArray[i] = studentArray[i+1];
				}
				System.out.println("Student successfully removed.");
				index--;
			}
			else {
				System.out.println("Student is not in the records!");
			}
		}
	}
	
	
	//asks user for student, prints info of student
	public void retrieveStudentInfo(Scanner in) {
		System.out.println("Enter the student's first name: ");
		String fname = in.nextLine();
		System.out.println("Enter the student's last name: ");
		String lname = in.nextLine();
		int studentIndex = getStudentIndex(fname, lname);
		
		if(studentIndex >=0) {
			studentArray[studentIndex].printInfo(studentArray[studentIndex]);
		}
		else {
			System.out.println("Student not in the records!");
		}
	}
	
	//returns index of student using first and last name, returns -1 if not in array
	public int getStudentIndex(String fname, String lname) {
		for(int i=0; i<index;i++) {
			if (studentArray[i].getFirstName().equals(fname) && studentArray[i].getLastName().equalsIgnoreCase(lname)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void modifyStudent(String[] args) {
		String fname = args[0];
		String lname = args[1];
		int studentIndex = getStudentIndex(fname, lname);
		
		if (!studentArray[studentIndex].getFirstName().equals(args[2])) {
			studentArray[studentIndex].setFirstName(args[2]);
			Window.resTextArea.append("Successfully changed first name to " + studentArray[studentIndex].getFirstName()+"\n");
		}
			
		if (!studentArray[studentIndex].getLastName().equals(args[3])) {
			studentArray[studentIndex].setLastName(args[3]);
			Window.resTextArea.append("Successfully changed last name to " + studentArray[studentIndex].getLastName()+"\n");
		}
		
		int newPhone = Integer.parseInt(args[4]);
		if (studentArray[studentIndex].getPhone()!=newPhone) {
			studentArray[studentIndex].setPhone(newPhone);
			Window.resTextArea.append("Successfully changed phone to " + studentArray[studentIndex].getPhone()+"\n");
		}
		
		if (!studentArray[studentIndex].getEmail().equals(args[5])) {
			studentArray[studentIndex].setEmail(args[5]);
			Window.resTextArea.append("Successfully changed email to " + studentArray[studentIndex].getEmail()+"\n");
		}
		
		if (!studentArray[studentIndex].getAddress().equals(args[6])) {
			studentArray[studentIndex].setAddress(args[6]);
			Window.resTextArea.append("Successfully changed address to " + studentArray[studentIndex].getAddress()+"\n");
		}
		
		if (!studentArray[studentIndex].getEmContact().equals(args[7])) {
			studentArray[studentIndex].setEmContact(args[7]);
			Window.resTextArea.append("Successfully changed emergency contact to " + studentArray[studentIndex].getEmContact()+"\n");
		}

		int newAge = Integer.parseInt(args[8]);
		if (studentArray[studentIndex].getAge()!=newAge) {
			studentArray[studentIndex].setAge(newAge);
			Window.resTextArea.append("Successfully changed age to " + studentArray[studentIndex].getAge()+"\n");
		}

		if (!studentArray[studentIndex].getClassLevel().equals(args[9])){
			studentArray[studentIndex].setClassLevel(args[9]);
			Window.resTextArea.append("Successfully changed class level to " + studentArray[studentIndex].getClassLevel()+"\n");
		}

		if (!studentArray[studentIndex].getMajor().equals(args[10])) {
			studentArray[studentIndex].setMajor(args[10]);
			Window.resTextArea.append("Successfully changed major to " + studentArray[studentIndex].getMajor()+"\n");
		}
			
		float checkGpa = Float.parseFloat(args[11]);
		if (studentArray[studentIndex].getGpa()!=checkGpa) {
			studentArray[studentIndex].setGpa(checkGpa);
			Window.resTextArea.append("Successfully changed GPA to " + studentArray[studentIndex].getGpa()+"\n");
		}		
	}
	
	//asks user for student, then asks user to choose what data to modify, that resets data to new info
	public void modifyStudent(Scanner in) {
		System.out.println("Enter the student's first name: ");
		String fname = in.nextLine();
		System.out.println("Enter the student's last name: ");
		String lname = in.nextLine();
		int studentIndex = getStudentIndex(fname, lname);
		if(studentIndex >=0) {
			System.out.println("Modification Choices: ");
			System.out.printf("1- First name \n2- Last name \n3- Phone \n4- Email \n5- Address \n6- Emergency Contact \n7- Age \n8- Class Level \n9- Major\n10- GPA\n");
			System.out.println("Enter your choice for modification(1-10): ");
			int modificationChoice = Integer.parseInt(in.nextLine());
			
			switch(modificationChoice) {
			case 1:
				System.out.println("New First Name: ");
				String newFname = in.nextLine();
				studentArray[studentIndex].setFirstName(newFname);
				System.out.println("Successfully changed first name to " + studentArray[studentIndex].getFirstName());
				break;
			case 2:
				System.out.println("New Last Name: ");
				String newLname = in.nextLine();
				studentArray[studentIndex].setLastName(newLname);
				System.out.println("Successfully changed last name to " + studentArray[studentIndex].getLastName());
				break;
			case 3:
				System.out.println("New Phone: ");
				int newPhone = Integer.parseInt(in.nextLine());
				studentArray[studentIndex].setPhone(newPhone);
				System.out.println("Successfully changed phone to " + studentArray[studentIndex].getPhone());
				break;
			case 4:
				System.out.println("New Email: ");
				String newEmail = in.nextLine();
				studentArray[studentIndex].setEmail(newEmail);
				System.out.println("Successfully changed email to " + studentArray[studentIndex].getEmail());
				break;
			case 5:
				System.out.println("New Address: ");
				String newAddress = in.nextLine();
				studentArray[studentIndex].setAddress(newAddress);
				System.out.println("Successfully changed address to " + studentArray[studentIndex].getAddress());
				break;
			case 6:
				System.out.println("New Emergency Contact: ");
				String newEContact = in.nextLine();
				studentArray[studentIndex].setEmContact(newEContact);
				System.out.println("Successfully changed emergency contact to " + studentArray[studentIndex].getEmContact());
				break;
			case 7:
				System.out.println("New Age: ");
				int newAge = Integer.parseInt(in.nextLine());
				studentArray[studentIndex].setAge(newAge);
				System.out.println("Successfully changed age to " + studentArray[studentIndex].getAge());
				break;
			case 8:
				System.out.println("New Class Level: ");
				String newClassLevel = in.nextLine();
				studentArray[studentIndex].setClassLevel(newClassLevel);
				System.out.println("Successfully changed class level to " + studentArray[studentIndex].getClassLevel());
				break;
			case 9:
				System.out.println("New Major: ");
				String newMajor = in.nextLine();
				studentArray[studentIndex].setMajor(newMajor);
				System.out.println("Successfully changed major to " + studentArray[studentIndex].getMajor());
				break;
			case 10:
				System.out.println("New GPA: ");
				float checkGpa = Float.parseFloat(in.nextLine());
				studentArray[studentIndex].setGpa(checkGpa,in);
				System.out.println("Successfully changed GPA to " + studentArray[studentIndex].getGpa());
				break;
			default:
				System.out.println("ERROR: WRONG CHOICE");
			}
		}
	}
}
