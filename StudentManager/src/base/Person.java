package base;

public class Person {
	protected String firstName;
	protected String lastName;
	protected int phone;
	protected String email;
	protected String address;	
	protected String emContact;
	protected int age;
	
	public Person() {
		
	}
	
	//Person constructor
	public Person(String fname, String lname, int phone, String email, String address, String emContact, int age) {
		firstName = fname;
		lastName = lname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.emContact = emContact;
		this.age = age;
	}
	
	//Declare getters and setters as static?
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fname) {
		this.firstName = fname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lname) {
		this.lastName = lname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmContact() {
		return emContact;
	}
	public void setEmContact(String emContact) {
		this.emContact = emContact;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}//end of class
