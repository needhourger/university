package base;
import java.util.Scanner;

public class UIClass {
	
	public static StudentRecords stuRecods=new StudentRecords();
	public static EmployeeRecords empRecods=new EmployeeRecords();
	
//	//prints choice options and asks for choice from user
//	public static int getChoice(Scanner in) {
//		
//		System.out.printf("1- Add Student \n2- Add Employee \n3- Remove Student \n4- Remove Employee \n5- View Student Record \n6- View Employee Record \n7- Modify Student \n8- Modify Employee \n9- Quit\n");
//		System.out.println("Enter your choice (1-9): ");
//		int choice = Integer.parseInt(in.nextLine());
//		return choice;
//
//	}
	
	//main
//	public static void main(String[] args) {
//		StudentRecords studentRecord = new StudentRecords();
//		EmployeeRecords employeeRecord = new EmployeeRecords();
//		
//		
//		int choice;
//		Scanner in = new Scanner(System.in);
//		choice = getChoice(in);
//		
//		while(choice!=9) {
//			switch(choice) {
//			case 1:
//				System.out.println("ADD STUDENT");
//				studentRecord.addStudent(in);
//				break;
//			case 2:
//				System.out.println("ADD EMPLOYEE");
//				employeeRecord.addEmployee(in);
//				break;
//			case 3:
//				System.out.println("REMOVE STUDENT");
//				studentRecord.removeStudent(in);
//				break;
//			case 4:
//				System.out.println("REMOVE EMPLOYEE");
//				employeeRecord.removeEmployee(in);
//				break;
//			case 5:
//				System.out.println("VIEW STUDENT");
//				studentRecord.retrieveStudentInfo(in);
//				break;
//			case 6:
//				System.out.println("VIEW EMPLOYEE");
//				employeeRecord.retrieveEmployeeInfo(in);
//				break;
//			case 7:
//				System.out.println("MODIFY STUDENT");
//				studentRecord.modifyStudent(in);
//				break;
//			case 8:
//				System.out.println("MODIFY EMPLOYEE");
//				employeeRecord.modifyEmployee(in);
//				break;
//			default:
//				System.out.println("ERROR: WRONG CHOICE");
//			}//end of switch
//			choice = getChoice(in);
//			
//		}//end of while
//		System.out.println("Thanks and goodbye!");
//		in.close();
//	}//end of main
}
