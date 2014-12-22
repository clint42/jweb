package model;

public class UserMailAlreadyUsedException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserMailAlreadyUsedException(String message) {
	        super(message);
	    }

	    public UserMailAlreadyUsedException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
