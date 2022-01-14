package Excepcions;

public class OutOfRangeException extends Exception {
	private static final long serialVersionUID = 1L;

	public OutOfRangeException(){
		super ("El número introduït es troba fora de rang.");
	}
}
