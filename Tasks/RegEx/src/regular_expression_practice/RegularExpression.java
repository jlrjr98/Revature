package regular_expression_practice;

public class RegularExpression {

	public static void main(String[] args) {
		
//	    [] = Expression
//	    {} = Length
//	    ^ = Not
		
		String s = "I like the numbers 123";
		System.out.println(s);
		System.out.println(s.replaceAll("[0-9]{3}", "one, two, and three."));
		
		String t = "jfguw4wruh&(*(&^UHHFsdlfhsk";
		System.out.println("\n" + t);
		System.out.println(t.replaceAll("[A-Z]", ""));
		System.out.println(t.replaceAll("[j,f]", "q"));
		System.out.println(t.replaceAll("[^l]", "z"));
		

	}

}
