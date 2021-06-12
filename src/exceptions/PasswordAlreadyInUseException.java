package exceptions;

/**
 * Exception thrown when the password is already used by some other element of the model. <br>
 * */
public class PasswordAlreadyInUseException extends Exception {

    /**
     * This is the main constructor of the class. <br>
     * */
	public PasswordAlreadyInUseException(){
        super("This password is already in use by other person");
    }
}
