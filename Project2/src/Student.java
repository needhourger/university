import javax.swing.JTextArea;

public class Student extends Person{
	private String classLevel;
	private String major;
	private float gpa;
	
	public Student() {
		super();
	}
	
	public Student(String fname, String lname, String phone, String email, String address, String emContact, int age, String classLevel, String major, float gpa) {
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
	public void setGpa(float gpa) {
		if(gpa >=0 && gpa <=4.0) {
			this.gpa = gpa;
		}
		else {
			this.gpa = 0.0f;
		}
		
	}
	//prints all info of student object
	public void printInfo(Student student, JTextArea textArea) {
		textArea.setText("First name: " + student.getFirstName()+"\nLast name: "+ student.getLastName()+ "\nPhone: "+student.getPhone()+"\nEmail: "+ student.getEmail()+ "\nAddress: " + student.getAddress()+ "\nEmergency Contact: "+ student.getEmContact()+"\nAge: "+ student.getAge()+"\nClass Level: " + student.getClassLevel()+"\nMajor: "+ student.getMajor() + "\nGPA: "+ student.getGpa() +"\n");
	}
}
