package main;


public class Main {
	@SuppressWarnings("unused")
	private static boolean islogin=false;
	public static Windows windows=null;
	
	public static void main(String[] args) {
		windows=new Windows(20);
		if (Windows.islogin==false) windows.login();
	}
}
