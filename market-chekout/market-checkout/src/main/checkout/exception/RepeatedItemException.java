package main.checkout.exception;

public class RepeatedItemException extends Exception{

	private static final long serialVersionUID = -1242672736434913648L;

	public RepeatedItemException(String message) {
		super(message);
	}
	
}
