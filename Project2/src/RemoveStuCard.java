import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RemoveStuCard extends JPanel{
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	
	private JButton removeButton = new JButton("Remove Student");
	public RemoveStuCard(StudentRecords studentRecords, JTextArea textArea) {
		
		setLayout(new GridLayout(3,2,100,250));
		
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fname = fNameTextField.getText();
				String lname = lNameTextField.getText();
				 
				//clear textfields
				fNameTextField.setText("");
				lNameTextField.setText("");
				
				studentRecords.removeStudent(fname, lname, textArea);
			}
		});
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
		add(removeButton);
		
		
	}
}
