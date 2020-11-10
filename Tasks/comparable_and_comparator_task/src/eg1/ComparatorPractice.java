package eg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorPractice implements Comparator<Object> {
	
	public static void main(String[] args) {
		
		Integer x = 10;
		Integer y = 5;
		
		int z = x.compare(x, y);
		System.out.println(z); //returns 1 because x is greater than y
		int w = y.compare(x, y);
		System.out.println(w); //retrns 1 because x is greater than y
		
		String str1 = "a";
		String str2 = "b";
		
		//int a = str1.compare(str1, str2); //no Strings
		
		int num1 = 1;
		int num2 = 2;
		
		//num1.comare(num1, num2);	//doesn't work with primitives	
		
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
		
		//Implementing of Comparator allows you to choose how a list is sorted
		Comparator<ListOfThings> com = new Comparator<ListOfThings>() {
			public int compare(ListOfThings l1 , ListOfThings l2) {
				if (l1.getSize() > l2.getSize()) {
					return 1;
				} else {
					return -1;
				}
			}
		};

			Collections.sort(l1, com);
			Collections.sort(l2, com);
			
			System.out.println(l1);
			System.out.println(l2);
	}

	//doesn't need to be implemented to use compare();
	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}

}
