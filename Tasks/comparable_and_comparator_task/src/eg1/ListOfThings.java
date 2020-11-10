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
	@Override
	public int compareTo(ListOfThings o) {		
		
		if (this.getSize() > o.getSize()) {
			return 1;
		}
		else if (this.getSize() < o.getSize()) {
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
