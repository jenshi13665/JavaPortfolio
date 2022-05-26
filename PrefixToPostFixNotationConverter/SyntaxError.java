/*The following class defines the exception utilized by 
 * the Converter2 and ConverterGUI classes.
 *
 *Name: Ingram, Chloe P		Date: 24 March 2022		Class: CMSC 350 - 7380
 */

public class SyntaxError extends Exception {
	
	public SyntaxError(String errorMessage) {
		super(errorMessage);
	}

}
