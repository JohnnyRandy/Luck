package test1227;

public class test {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
//		String s1=new String("hello");
//		String s2=new String("hello");
//		System.out.println(s1==s2);
//		System.out.println(s1.equals(s2));
//		
//		String s3="hello";
//		String s4="hello";
//		System.out.println(s3==s4);
		
//		char c[]={'s','u','n',' ','j','a','v','a'};
//		String s5=new String(c);
//		String s6=new String(c,4,4);
//		System.out.println(s5);
//		System.out.println(s6);
		
		
		
		String s1="sun java";
		String s2="Sun Java";
		System.out.println(s1.charAt(1));//u
		System.out.println(s2.length());//  8
		System.out.println(s1.indexOf("java"));//4
		System.out.println(s1.indexOf("Java"));//-1
		System.out.println(s1.equals(s2));
		System.out.println(s1.equalsIgnoreCase(s2));
		
		String s="我在学Java";
		String sr=s.replace('我', '你');
		System.out.println(s);
		
	}

}
