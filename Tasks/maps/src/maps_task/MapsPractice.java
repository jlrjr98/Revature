package maps_task;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapsPractice {

	public static void main(String[] args) {

		//Create
		Map<Integer, String> m1 = new HashMap<>();
		m1.put(1200, "Hello");
		m1.put(1384, "Cheese");
		m1.put(5037, "Bubble");
		m1.put(4693, "Blanket");
		m1.put(null, null); //can have null key and value
		
		//Read	
		System.out.println(m1);
		
		//Update
		m1.put(1200, "update");
		System.out.println(m1);
		
		//Delete
		m1.remove(4693);
		System.out.println(m1);
		
		//Create
		Map<Integer, String> ht = new Hashtable<>();
		ht.put(1200, "Hello");
		ht.put(1384, "Cheese");
		ht.put(5037, "Bubble");
		ht.put(4693, "Blanket");
		//ht.put(null, null); //can't have null key or value
		
		//Read	
		System.out.println(ht);
		
		//Update
		ht.put(1200, "update");
		System.out.println(ht);
		
		//Delete
		ht.remove(4693);
		System.out.println(ht);
		
		
		
		//Create
		Map<Integer, String> m2 = new LinkedHashMap<>();
		m2.put(1200, "Hello");
		m2.put(1384, "Cheese");
		m2.put(5037, "Bubble");
		m2.put(4693, "Blanket");
		m2.put(null, null); //can have null key and value
		
		//Read	
		System.out.println("\n\n" + m2);
		
		//Update
		m2.put(1200, "update");
		System.out.println(m2);
		
		//Delete
		m2.remove(4693);
		System.out.println(m2);
		
		
		
		//Create
		Map<Integer, String> m3 = new TreeMap<>();
		m3.put(1200, "Hello");
		m3.put(1384, "Cheese");
		m3.put(5037, "Bubble");
		m3.put(4693, "Blanket");
		m3.put(2300, null); //can't have null key, but can have null value
		
		//Read	
		System.out.println("\n\n" + m3);
		
		//Update
		m3.put(1200, "update");
		System.out.println(m3);
		
		//Delete
		m3.remove(4693);
		System.out.println(m3);


	}

}
