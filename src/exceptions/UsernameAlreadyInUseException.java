package exceptions;

/**
 * Exception thrown when the username is already used by some other element of the model. <br>
 * */
public class UsernameAlreadyInUseException extends Exception {

    /**
     * This is the main constructor of the class. <br>
     * */
	public UsernameAlreadyInUseException(){
        super("This username is already in use by other person");
    }
}
