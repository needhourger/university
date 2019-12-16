import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentRecords {
	ArrayList<Student> studentArray = new ArrayList<Student>();
	int index;
	public StudentRecords() {
		index = 0;
	}
	//asks user for student data, creates new Student object, and adds to studentArray
	public void addStudent(String fname, String lname, String phone, String email, String address, String emContact, String age, String classLevel, String major, String gpa) {
//		if(index>studentArray.size()) {
//			System.out.println("The student records are full!");
//			return;
//		}
		Student newStudent = new Student(fname, lname, phone, email, address, emContact, Integer.parseInt(age),classLevel,major,Float.parseFloat(gpa));
		
//		newStudent.setFirstName(fname);
//		newStudent.setLastName(lname);
//		newStudent.setPhone(phone);
//		newStudent.setEmail(email);
//		newStudent.setAddress(address);
//		newStudent.setEmContact(emContact);
//		newStudent.setAge(Integer.parseInt(age));
//		newStudent.setClassLevel(classLevel);
//		newStudent.setMajor(major);
//		newStudent.setGpa(Float.parseFloat(gpa));
		
		studentArray.add(newStudent);
		//studentArray.set(index, newStudent);
		index++;
	}
	
	//asks user for student, moves objects to left and decrements index value
	public void removeStudent(String fname, String lname, JTextArea textArea) {
		int i = getStudentIndex(fname, lname);
		if (i==-1) {
			textArea.setText("Student is not in the records!");
			return;
		}
		studentArray.remove(i);
		textArea.setText("Student successfully removed.");
		index--;
//		if(i>=0) {
//			for(int pos=i;pos<index;pos++) {
//				if((pos+1)==studentArray.size()-index-1) {
//					studentArray.set(pos, null);
//					textArea.setText("Student successfully removed.");
//					return;
//				}
//				else {
//					studentArray.set(pos, studentArray.get(pos+1));
//				}
//			}
//			//textArea.setText("Student successfully removed.");
//			index--;
//		}
//		else {
//			textArea.setText("Student is not in the records!");
//		}
	}
	
	
	//asks user for student, prints info of student
	public void retrieveStudentInfo(String fname, String lname, JTextArea textArea) {
		int studentIndex = getStudentIndex(fname, lname);
		
		if(studentIndex >=0) {
			studentArray.get(studentIndex).printInfo(studentArray.get(studentIndex), textArea);
		}
		else {
			textArea.setText("Student not in the records!");
		}
	}
	
	//returns index of student using first and last name, returns -1 if not in array
	public int getStudentIndex(String fname, String lname) {
		for(int i=0; i<index;i++) {
			if (studentArray.get(i).getFirstName().equals(fname) && studentArray.get(i).getLastName().equalsIgnoreCase(lname)) {
				return i;
			}
		}
		
		return -1;
	}
	
	//asks user for student, then asks user to choose what data to modify, that resets data to new info
	public void modifyStudent(String fname, String lname, JTextArea textArea, JTextField fNameTextField2, JTextField lNameTextField2, JTextField phoneTextField, JTextField emailTextField, JTextField addressTextField, JTextField emContactTextField, JTextField ageTextField, JComboBox<String> classLevelComboBox, JTextField majorTextField,JTextField gpaTextField){
		int studentIndex = getStudentIndex(fname, lname);
		if(studentIndex >=0) {
			fNameTextField2.setText(studentArray.get(studentIndex).getFirstName());
			lNameTextField2.setText(studentArray.get(studentIndex).getLastName());
			phoneTextField.setText(studentArray.get(studentIndex).getPhone());
			emailTextField.setText(studentArray.get(studentIndex).getEmail());
			addressTextField.setText(studentArray.get(studentIndex).getAddress());
			emContactTextField.setText(studentArray.get(studentIndex).getEmContact());
			ageTextField.setText(Integer.toString(studentArray.get(studentIndex).getAge()));
			classLevelComboBox.setSelectedItem(studentArray.get(studentIndex).getClassLevel());
			majorTextField.setText(studentArray.get(studentIndex).getMajor());
			gpaTextField.setText(Float.toString(studentArray.get(studentIndex).getGpa()));
	
		}
		else {
			textArea.setText("Student not in the records.");
		}
	}
	
	public void submitChanges(String fname, String lname, String fname2,String lname2, String phone,String email,String address,String emContact, int age, String classLevel, String major, Float gpa) {
		int studentIndex = getStudentIndex(fname,lname);
		Student student = new Student(fname2, lname2, phone, email, address,emContact,age,classLevel,major,gpa);
		studentArray.set(studentIndex, student);
	}
}
