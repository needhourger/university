import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

class MyException extends Exception{
	/**
	 * MyException
	 * handle division by zero
	 */
	private static final long serialVersionUID = 5304364383804831585L;
	public MyException() {
		super();
	}
	public MyException(String message) {
		super(message);
	}
}

public class Calculator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5813617939265490296L;

	
	private JPanel panel;
	private JTextField inpuTextField;
	private JLabel historyLabel;
	private String input;
	private String history;
	private int historyNum;
	
	public Calculator() {
		panel=new JPanel();
		
		inpuTextField=new JTextField(30);
		inpuTextField.setEditable(false);
		inpuTextField.setHorizontalAlignment(JTextField.LEFT);
		inpuTextField.setPreferredSize(new Dimension(190,30));
		this.add(inpuTextField,BorderLayout.NORTH);
		
		history="<html></html>";
		historyNum=0;
		historyLabel=new JLabel(history);
		historyLabel.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		historyLabel.setHorizontalAlignment(JLabel.LEFT);
		historyLabel.setPreferredSize(new Dimension(200,200));
		this.add(historyLabel,BorderLayout.AFTER_LAST_LINE);
		
		input=new String("");
		String[] name= {"%","Call","del","C",
						"7","8","9","+",
						"4","5","6","-",
						"1","2","3","*",
						"0",".","/","+/-",
						"","","","="};
		panel.setLayout(new GridLayout(6,4,2,2));
		
		for (int i=0;i<name.length;i++) {
			JButton button=new JButton(name[i]);
			button.addActionListener(new MyActionListener());
			panel.add(button);
		}
		this.add(panel,BorderLayout.CENTER);
	}
	
	/** 
	 * buttons' action listener 
	 */
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int cnt=0;
			String res = "";
			String actionCommand=e.getActionCommand();
			if (actionCommand.equals("+") || actionCommand.equals("-")|| actionCommand.equals("*")|| actionCommand.equals("/") ) {
				input+=" "+actionCommand+" ";
			}
			else if (actionCommand.equals("C")) {
				input="";
			}
			else if (actionCommand.equals("=")) {
				try {
					res=calculate(input);
					input+="="+res;
				} catch (Exception e2) {
					if (e2.getMessage()=="empty String") {
						input="";
					}else {
						input=e2.getMessage();
					}
						
					// TODO: handle exception
				}
				inpuTextField.setText(input);
				if (historyNum<10 && !input.isBlank()) {
					history=history.replaceFirst("</html>", input+"<br/></html>");
					historyLabel.setText(history);
					historyNum++;
				}
				input=res;
				cnt=1;
			}
			else if (actionCommand.equals("%")) {
				try {
					res=calculate(input,"%");
					input+="="+res;
				} catch (Exception e2) {
					if (e2.getMessage()=="empty String") {
						input="";
					}else {
						input=e2.getMessage();
					}
						
					// TODO: handle exception
				}
				inpuTextField.setText(input);
				if (historyNum<10 && !input.isBlank()) {
					history=history.replaceFirst("</html>", input+"<br/></html>");
					historyLabel.setText(history);
					historyNum++;
				}
				input=res;
				cnt=1;
			}
			else if (actionCommand.equals("del")) {
				if (input.length()>0)
					input=input.substring(0,input.length()-1);
			}
			else if (actionCommand.equals("+/-")) {
				if (!input.isBlank()) {
					try {
						String[] temp=input.split(" ");
						double x=-Double.parseDouble(temp[temp.length-1]);
						input=input.replaceFirst(temp[temp.length-1], String.valueOf(x));
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
			else if (actionCommand.equals("Call")) {
				history="<html></html>";
				historyLabel.setText(history);
				historyNum=0;
			}
			else input+=actionCommand;
			if (cnt==0) inpuTextField.setText(input);
		}
		
		/**
		 * the function for handling the calculation
		 */
		private String calculate(String input, String string) throws MyException {
			
			String[] comput=input.split(" ");
			Stack<Double> stack=new Stack<>();
			Double m=Double.parseDouble(comput[0]);
			stack.push(m);
			
			for (int i=1;i<comput.length;i++) {
				if (i%2==1) {
					if (comput[i].equals("+"))
						stack.push(Double.parseDouble(comput[i+1]));
					if (comput[i].equals("-"))
						stack.push(-Double.parseDouble(comput[i+1]));
					if (comput[i].equals("*")) {
						Double d=stack.peek();
						stack.pop();
						stack.push(d*Double.parseDouble(comput[i+1]));
					}
					if (comput[i].equals("/")) {
						double help=Double.parseDouble(comput[i+1]);
						if (help==0)
							throw new MyException("The dividend cannot be 0");
						double d=stack.peek();
						stack.pop();
						stack.push(d/help);
					}
				}
			}
			
			double d=0d;
			while (!stack.isEmpty()) {
				d+=stack.peek();
				stack.pop();
			}
			
			String ret=String.format("%.2f%%", 100*d);
			return ret;
		}

		private String calculate(String input) throws MyException {
			String[] comput=input.split(" ");
			Stack<Double> stack=new Stack<>();
			Double m=Double.parseDouble(comput[0]);
			stack.push(m);
			
			for (int i=1;i<comput.length;i++) {
				if (i%2==1) {
					if (comput[i].equals("+"))
						stack.push(Double.parseDouble(comput[i+1]));
					if (comput[i].equals("-"))
						stack.push(-Double.parseDouble(comput[i+1]));
					if (comput[i].equals("*")) {
						Double d=stack.peek();
						stack.pop();
						stack.push(d*Double.parseDouble(comput[i+1]));
					}
					if (comput[i].equals("/")) {
						double help=Double.parseDouble(comput[i+1]);
						if (help==0)
							throw new MyException("The dividend cannot be 0");
						double d=stack.peek();
						stack.pop();
						stack.push(d/help);
					}
				}
			}
			
			double d=0d;
			while (!stack.isEmpty()) {
				d+=stack.peek();
				stack.pop();
			}
			
			String ret=String.format("%.2f", d);
			return ret;
		}
		
	}
	
	/**
	 * the main function
	 */
	public static void main(String[] args) {
		Calculator f=new Calculator();
		f.setTitle(f.getClass().getSimpleName());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
	}
}
