package eg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparablePractice  {

	public static void main(String[] args) {	
		
		Integer x = 10;
		Integer y = 5;
		
		//comparing 2 objects
		int r = x.compareTo(y);
		System.out.println(r); //returns 1 because x is greater
		int b = y.compareTo(x);
		System.out.println(b); //returns -1 because y 
		
//		int i = 3;
//		int n = 4;
		
		//i.compareTo(n);	//doesn't work on primitives. Must be objects.
		
		String str1 = "a";
		String str2 = "b";
		
		int s = str1.compareTo(str2);
		System.out.println(s); //returns -1 because a is 'lesser' than b
		int c = str2.compareTo(str1);
		System.out.println(c); //returns 1 because b is 'greater' than a
		
		//int z = str1.compareTo(y);	//objects must be same type
		
		
		List<ListOfThings> l1 = new ArrayList<>();
		ListOfThings thing1 = new ListOfThings("Don", 45);
		ListOfThings thing2 = new ListOfThings("Ball", 6);
		ListOfThings thing3 = new ListOfThings("Sky", 27);
		l1.add(thing1);
		l1.add(thing2);
		l1.add(thing3);
		
		List<ListOfThings> l2 = new ArrayList<>();
		ListOfThings thing4 = new ListOfThings("Ground", 42);
		ListOfThings thing5 = new ListOfThings("Hello", 77);
		ListOfThings thing6 = new ListOfThings("Whale", 500);
		l2.add(thing4);
		l2.add(thing5);
		l2.add(thing6);
		
		System.out.println(l1);
		System.out.println(l2);
		
		//implementing comparable allows for sorting of lists
		Collections.sort(l1);
		Collections.sort(l2);
		
		System.out.println(l1);
		System.out.println(l2);
		
		
	}

//	//doesn't need to be implemented to use compareTo();
//	@Override
//	public int compareTo(Object o) {
//		return 0;
//	}

}
