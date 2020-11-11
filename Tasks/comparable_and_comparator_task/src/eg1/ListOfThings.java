package eg1;

public class ListOfThings implements Comparable<ListOfThings> {
	//implements Comparable to allow for comparing of a list of these custom objects
	
	private String name;
	private int size;
	
	public ListOfThings(String name, int size) {
		super();
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	//needed for sorting this list
	//determines custom way a list is sorted.
	@Override
	public int compareTo(ListOfThings o) {		
		
		if (this.getName().charAt(0) > o.getName().charAt(0)) {
			return 1;
		}
		else if (this.getName().charAt(0) < o.getName().charAt(0)) {
			return -1;
		}
		else {
			return 0;
		}
		
	}

	@Override
	public String toString() {
		return "ListOfThings [name=" + name + ", size=" + size + "]";
	}
	
	

}
