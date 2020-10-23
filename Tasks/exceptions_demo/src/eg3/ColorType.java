package eg3;

public class ColorType {
	
	//class to check validity of color
	public boolean IsValidColorType(String color) throws ColorTypeException {
		
		//checks if color is valid or not
		if (color == "blue") {
			throw new ColorTypeException("Invalid Color");
		}
		return true;
		
	}

}
