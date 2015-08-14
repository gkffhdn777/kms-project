package kms.com.jpa.exception;

@SuppressWarnings("serial")
public class NotEnoughStockException  extends RuntimeException{

	
	public NotEnoughStockException(String messge) {
		super(messge);
	}
	
}
