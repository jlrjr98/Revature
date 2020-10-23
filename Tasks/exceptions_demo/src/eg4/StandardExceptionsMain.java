package eg4;

public class StandardExceptionsMain {
	
	public static void main(String[] args) {
        
		//data
        int x = 10;
        int y = 0;

        //try-catch
        try {
            int res = x / y;
        }
        //pre-existing exception
        catch (ArithmeticException e) {
            //custom message
            System.out.println("Cannot divide by zero");
        }
        


    }

}
