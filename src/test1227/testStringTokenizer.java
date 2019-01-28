package test1227;

import java.util.StringTokenizer;

public class testStringTokenizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String s1="public-static-void-main";
		 StringTokenizer str=new StringTokenizer(s1,"-");
		 int n=str.countTokens();
		 System.out.println("s1ÓÐµ¥´Ê"+n+"¸ö");
		 
		 while(str.hasMoreElements()){
			String s= str.nextToken();
			System.out.print(" "+s);
			 
		 }

	}

}
