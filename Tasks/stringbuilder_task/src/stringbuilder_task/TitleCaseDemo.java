package stringbuilder_task;

public class TitleCaseDemo {

	public static void main(String[] args) {
		String s="hello hi good morning I am enjoying programming with java";
		StringBuilder sb=new StringBuilder();
		String ar[]=s.split(" ");
		for(String s1:ar) {
			sb.append(Character.toUpperCase(s1.charAt(0))).append(s1.substring(1)).append(" ");
		}
		System.out.println(sb.toString().trim());
		
		
		//Task - Convert every words last char to uppercase
		
		
		String a = "hello hi good morning I am enjoying programming with java";
		StringBuilder sb1 = new StringBuilder();
		String ar1[] = a.split(" ");
		
		for (String s1:ar1) {	
			
			sb1.append(s1.substring(0, s1.length() -1)).append(Character.toUpperCase(s1.charAt(s1.length() -1))).append(" ");
			
		}
		
		System.out.println(sb1.toString().trim());

	}
	
}