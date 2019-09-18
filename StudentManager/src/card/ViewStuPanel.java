package card;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.Student;
import base.UIClass;
import window.Window;

public class ViewStuPanel extends JPanel {
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	private JLabel phoneLabel = new JLabel("Phone: ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JLabel addressLabel = new JLabel("Address: ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age: ");
	private JLabel classLevelLabel= new JLabel("Class level: ");
	private JLabel majorLabel = new JLabel("Major: ");
	private JLabel gpaLabel = new JLabel("GPA: ");
	
	private static String[] classLevels = {"First-year","Sophomore","Junior","Senior"};
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	private JTextField phoneTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField addressTextField = new JTextField();
	private JTextField emContactTextField = new JTextField();
	private JTextField ageTextField = new JTextField();
	private JComboBox<String> classLevelComboBox = new JComboBox<String>(classLevels);
	private JTextField majorTextField = new JTextField();
	private JTextField gpaTextField = new JTextField();
	
	public void update(String fName,String lName) {
		int i=UIClass.stuRecods.getStudentIndex(fName, lName);
		if (i==-1) {
			Window.resTextArea.append("Student not in the record!\n");
			return;
		}
		Student temp=UIClass.stuRecods.studentArray[i];
		fNameTextField.setText(temp.getFirstName());
		lNameTextField.setText(temp.getLastName());
		phoneTextField.setText(String.valueOf(temp.getPhone()));
		emailTextField.setText(temp.getEmail());
		addressTextField.setText(temp.getAddress());
		emContactTextField.setText(temp.getEmContact());
		ageTextField.setText(String.valueOf(temp.getAge()));
		classLevelComboBox.setSelectedItem(temp.getClassLevel());
		majorTextField.setText(temp.getMajor());
		gpaTextField.setText(String.valueOf(temp.getGpa()));
		
	}

	public ViewStuPanel() {
		super();
		
		setLayout(new GridLayout(10,2));
		
		fNameTextField.setEditable(false);
		lNameTextField.setEditable(false);
		phoneTextField.setEditable(false);
		emailTextField.setEditable(false);
		addressTextField.setEditable(false);
		emContactTextField.setEditable(false);
		ageTextField.setEditable(false);
		classLevelComboBox.setEditable(false);
		majorTextField.setEditable(false);
		gpaTextField.setEditable(false);
		
		add(fNameLabel);
		add(fNameTextField);
		add(lNameLabel);
		add(lNameTextField);
		add(phoneLabel);
		add(phoneTextField);
		add(emailLabel);
		add(emailTextField);
		add(addressLabel);
		add(addressTextField);
		add(emContactLabel);
		add(emContactTextField);
		add(ageLabel);
		add(ageTextField);
		add(classLevelLabel);
		add(classLevelComboBox);
		add(majorLabel);
		add(majorTextField);
		add(gpaLabel);
		add(gpaTextField);
	}
}
