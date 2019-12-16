package card;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.UIClass;

public class RemoveEmpCard extends JPanel{
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	
	private JButton removeButton = new JButton("Remove Employee");
	public RemoveEmpCard() {
		setLayout(new GridLayout(3,2,10,160));
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
		
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName=fNameTextField.getText();
				String lastName=lNameTextField.getText();
				String[] args= {firstName,lastName};
				UIClass.empRecods.removeEmployee(args);
				fNameTextField.setText("");
				lNameTextField.setText("");
			}
		});
		add(removeButton);
	}
}