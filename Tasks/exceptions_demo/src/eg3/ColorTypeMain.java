package eg3;

public class ColorTypeMain {

	public static void main(String[] args) {
		
		//create instance
		ColorType c = new ColorType();
		
		try {
			
			//pass "blue" through this method to check if it's valid
			if (c.IsValidColorType("blue")) {
				
				//message if valid
				System.out.println("Good");
			}
			
			//catch (Class that includes exception message e)
		} catch (ColorTypeException e) {
			
			//message if isn't valid
			System.out.println(e.getMessage());
		}

	}

}
