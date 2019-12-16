package base;
import java.util.Scanner;

import window.Window;

public class Student extends Person{
	private String classLevel;
	private String major;
	private float gpa;
	
	public Student() {
		super();
	}
	
	public Student(String fname, String lname, int phone, String email, String address, String emContact, int age, String classLevel, String major, float gpa) {
		super(fname, lname, phone, email, address, emContact, age);
		this.classLevel = classLevel;
		this.major = major;
		this.gpa = gpa;
	}
	
	public String getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa,Scanner in) {
		if(gpa >=0 && gpa <=4.0) {
			this.gpa = gpa;
		}
		else {
			System.out.println("Enter a valid gpa: ");
			float x=in.nextFloat();
			setGpa(x, in);
		}
		this.gpa = gpa;
	}
	
	public void setGpa(float gpa) {
		if(gpa >=0 && gpa <=4.0) {
			this.gpa = gpa;
		}
		else {
			Window.resTextArea.append("Enter a valid gpa: ");
		}
		this.gpa = gpa;
	}
	//prints all info of student object
	public void printInfo(Student student) {
		System.out.printf("First name: " + student.getFirstName()+"\nLast name: "+ student.getLastName()+ "\nPhone: "+student.getPhone()+"\nEmail: "+ student.getEmail()+ '\n');
		System.out.printf("Address: " + student.getAddress()+ "\nEmergency Contact: "+ student.getEmContact()+"\nAge: "+ student.getAge()+"\nClass Level: " + student.getClassLevel());
		System.out.printf("\nMajor: "+ student.getMajor() + "\nGPA: "+ student.getGpa() +"\n");
	}
}
