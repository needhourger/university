package simpleapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Operations {
	
	public static String calculate(String input) throws MyException{
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
		
		String ret=String.format("%.2f", boundaryCheck(d));
		return ret;
	}
	
	
	public static String calculate(String input, String string) throws MyException {
		
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
		
		String ret=String.format("%.2f%%", 100*boundaryCheck(d));
		return ret;
	}
	
	private static double boundaryCheck(double x) throws MyException {
		String[] strs=String.valueOf(x).split("\\.");
		String temp=strs[strs.length-1];
		int len=temp.length();
		if (x>Long.MAX_VALUE || x<-Long.MAX_VALUE || len>5)
			throw new MyException("Out of boundary");
		return x;		
	}
	
}

class MyActionListener implements ActionListener{
	
	private static boolean flag;
	
	public MyActionListener() {
		super();
		flag=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int cnt=0;
		String res = "";
		String actionCommand=e.getActionCommand();
		if (actionCommand.equals("+") || actionCommand.equals("-")|| actionCommand.equals("*")|| actionCommand.equals("/") ) {
			MainControl.input+=" "+actionCommand+" ";
			flag=false;
		}
		else if (actionCommand.equals("CE")) {
			MainControl.input="";
		}
		else if (actionCommand.equals("=")) {
			try {
				res=MainControl.calculate();
				MainControl.input+="="+res;
			} catch (Exception e2) {
				if (e2.getMessage()=="Out of boundary" || e2.getMessage()=="The dividend cannot be 0") {
					MainControl.input=e2.getMessage();
				}else {
					MainControl.input="";
				}
				// TODO: handle exception
			}
			MainControl.updateTextAndHistory();
			MainControl.input=res;
			flag=true;
			cnt=1;
		}
		else if (actionCommand.equals("%")) {
			try {
				res=MainControl.calculate("%");
				MainControl.input+="="+res;
			} catch (Exception e2) {
				if (e2.getMessage()=="Out of boundary" || e2.getMessage()=="The dividend cannot be 0") {
					MainControl.input=e2.getMessage();
				}else {
					MainControl.input="";
				}	
				// TODO: handle exception
			}
			MainControl.updateTextAndHistory();
			MainControl.input=res;
			flag=true;
			cnt=1;
		}
		else if (actionCommand.equals("<=")) {
			if (MainControl.input.length()>0)
				MainControl.input=MainControl.input.substring(0,MainControl.input.length()-1);
			flag=false;
		}
		else if (actionCommand.equals("+/-")) {
			if (MainControl.input!="") {
				try {
					String[] temp=MainControl.input.split(" ");
					double x=-Double.parseDouble(temp[temp.length-1]);
					MainControl.input=MainControl.input.replaceFirst(temp[temp.length-1], String.valueOf(x));
				} catch (Exception e2) {
					// TODO: handle exception
				}
				flag=false;
				
			}
		}
		else if (actionCommand.equals("C")) {
			flag=false;
			MainControl.clearAll();
		}
		else {
			if (flag) MainControl.input="";
			MainControl.input+=actionCommand;
			flag=false;
		}
		if (cnt==0) MainControl.updateText();
		
	}
	
}

class MyException extends Exception{
	/**
	 * handle my exception
	 */
	public MyException() {
		super();
	}
	public MyException(String message) {
		super(message);
	}
}
