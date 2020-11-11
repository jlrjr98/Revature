package eg1;

import java.util.Date;
import java.util.Formatter;

public class StringFormattingPractice {
	
	public static void main(String[] args) {
		String str = String.format("%d", 101);		
		System.out.println(str);
		
//        %n - takes no argument, returns line separator (%n = \n)
//        %d - takes integer, returns decimal integer
//        %s - takes any datatype, returns String value
//        %f - takes float value, returns decimal number
		
		String str1 = String .format("The number %d is a good number, but not as good as %d.", 12, 17);
		System.out.println(str1);
		
		String str2 = String.format("Line 1%nLine 2%nLine 3%n");
		System.out.println(str2);
		
		String str3 = String.format("The number %s is now a String", 12.9);
		System.out.println(str3);
		
		float f = 3.14f;
		String str4 = String.format("%f", f);
		System.out.println(str4);
		
		StringBuilder sb = new StringBuilder();
		Formatter fm = new Formatter(sb);
		fm.format("%s is %d years old", "Bobby", 12);
		System.out.println(sb.toString());
		fm.format(", and %s is %d years older", "Sally", 2);
		System.out.println(sb.toString());
		
		fm.close();
		
		String b = null;
		
		String str5 = String.format("%nThe variable is %b", b);
		System.out.println(str5);
		
		b = "hello";
		
		String str6 = String.format("%nThe variable is %b", b);
		System.out.println(str6);
		
		char c = 'a';
		
		String str7 = String.format("%c", c);
		System.out.println(str7);
		
		
		Date date = new Date();
		
		String str8 = String.format("%nThe current month is  %tB", date);
		System.out.println(str8);
		
	}			
}
