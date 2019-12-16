package simpleapp;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextPanel extends JPanel {
	private JTextField textField;
	
	public TextPanel() {
		textField=new JTextField(30);
		textField.setEditable(false);
		textField.setHorizontalAlignment(JTextField.LEFT);
		textField.setPreferredSize(new Dimension(190,30));
		this.add(textField);
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
}
