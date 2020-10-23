package linkList_priorityQueue_task;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Comparison {

	public static void main(String[] args) {
		
		Queue ql = new LinkedList();
		Queue qp = new PriorityQueue();
		
		ql.add("abc");
		ql.add(123);
		ql.add(true);
		ql.add('l');
		ql.add(3.1415f);
		System.out.println("ql = " + ql);
		
		//PriorityQueue can only add things of the same type.
		qp.add("abc");
		qp.add("yess");
//		qp.add(123);
//		qp.add(true);
//		qp.add('l');
//		qp.add(3.1415f);
		System.out.println("qp = " + qp);
		
		Queue<Integer> ql2 = new LinkedList<>();
		ql2.add(1);
		ql2.add(2);
		ql2.add(3);
		System.out.println("\nql2 = " + ql2);
		ql2.add(1);
		ql2.add(2);
		ql2.add(3);
		System.out.println("ql2 = " + ql2);
		//Linklist orders items in the order they were added.
		
		//ql2.add(1, 66);
		//LinkedList can't insert things at specific locations when interfaced via Queue
		
		List<Integer> ql3 = new LinkedList();
		ql3.add(1);
		ql3.add(2);
		ql3.add(3);
		System.out.println("\nql3 = " + ql3);
		
		ql3.add(1, 66);
		System.out.println("ql3 = " + ql3);		
		//Linklist CAN insert things at specific locations when interfaced via List
		
		Queue<Integer> qp2 = new PriorityQueue<>();
		qp2.add(1);
		qp2.add(2);
		qp2.add(3);
		System.out.println("\nqp2 = " + qp2);
		qp2.add(1);
		System.out.println("qp2 = " + qp2);
		qp2.add(2);
		System.out.println("qp2 = " + qp2);
		qp2.add(3);
		System.out.println("qp2 = " + qp2);
		qp2.add(1);
		System.out.println("qp2 = " + qp2);
		qp2.add(2);
		System.out.println("qp2 = " + qp2);
		qp2.add(3);
		System.out.println("qp2 = " + qp2);
		//whenever a new integer is added, the PriorityQueue collection sorts the lowest numbers at the front
		//all other numbers are sorted by order they were added
		
		//qp2.add(1, 66);
		//PriorityQueue can't insert things at specific locations
		
		//ql2.set(0, 70);
		ql3.set(0, 70);
		System.out.println("\nql3 = " + ql3);
		//LinkedList can't use .set to update when interfaced via Queue
		//It can do so when interfaced via List
		
		//qp2.set(0, 70);
		//PriorityQueue can't use .set to update
		
		ql2.add(1);
		System.out.println("\nql2 = " + ql2);
		ql2.remove(1);
		System.out.println("ql2 = " + ql2);
		//.remove deletes the first instance of the provided int
		
		ql3.remove(1);
		System.out.println("ql3 = " + ql3);
		//.remove deletes the int at the index provided.

		qp2.remove(1);
		System.out.println("qp2 = " + qp2);
		//.remove deletes one instance of the provided int (presumably the first instance) and also reorders the collection.

		Integer i = 1;
		ql2.remove(i);
		System.out.println("\nql2 = " + ql2);
		//deletes first instance of int 1
		
		Integer j = 1;
		qp2.remove(j);
		System.out.println("qp2 = " + qp2);
		//deletes instance of int 1 (presumably the first instance)
		
		Integer k = 3;
		while(ql2.remove(k)) {} 
		System.out.println("\nql2 = " + ql2);
		//removes all instances of 3
		
		Integer c = 3;
		while(qp2.remove(c)) {} 
		System.out.println("qp2 = " + qp2);
		//removes all instances of 3
		
//		System.out.println("ql2.get(3) = " + ql2.get(3));
//		System.out.println("qp2.get(3) = " + qp2.get(3));
		System.out.println("\nql3.get(2) = " + ql3.get(2));
		//can't use .get in the Queue interface, but can in the List interface
		
		System.out.println("\nql2.size() = " + ql2.size());
		System.out.println("qp2.size() = " + qp2.size());
		//can use .size() to get number of elements in list
		
		System.out.println("\nql2.contains(100) = " + ql2.contains(100));
		System.out.println("qp2.contains(100) = " + qp2.contains(1));
		//can use .constains() to see if provided int exists in collection
		
		
		System.out.println("\nIterating using a for each loop");
		for (Integer i2:ql2) {
			System.out.println(i2);
		}
		
		System.out.println("\nIterating using a for each loop");
		for (Integer i2:qp2) {
			System.out.println(i2);
		}
		//for each loop works for both
		
		//Collections.reverse(ql2);
		//Collections.reverse(qp2);
		Collections.reverse(ql3);
		System.out.println("\nql3 = " + ql3);
		//Can't use .reverse with Queue interface
		
		//Collections.shuffle(ql2);
		//Collections.shuffle(qp2);
		Collections.shuffle(ql3);
		System.out.println("\nql3 = " + ql3);
		//Can't use .shuffle with Queue interface;
		
		//Collections.sort(ql2, Collections.reverseOrder());
		//Collections.sort(qp2, Collections.reverseOrder());
		Collections.sort(ql3, Collections.reverseOrder());
		System.out.println("\nql3 = " + ql3);
		//Can't use .sort with Queue interface
		
		//System.out.println("\n" + Collections.binarySearch(ql2, 100));
		//System.out.println("\n" + Collections.binarySearch(qp2, 100));
		System.out.println("\n" + Collections.binarySearch(ql3, 100));
		//Can't use .binarySearch with Queue interface
	}


}
