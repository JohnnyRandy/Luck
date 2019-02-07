package test1227;

public class test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s="Welcome to Java World!";
		String s1="  sun java  ";
		System.out.println(s.startsWith("Welcome"));//true
		System.out.println(s.endsWith("World"));//false
		
		String sL=s.toLowerCase();
		String sU=s.toUpperCase();
		System.out.println(sL);
		System.out.println(sU);
		
		String subs=s.substring(11,13);//Java World!
		System.out.println(subs);
		
		String sp=s1.trim();
		System.out.println(sp);
		
		

	}

}
