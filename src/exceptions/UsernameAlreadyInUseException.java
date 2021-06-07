package exceptions;

public class UsernameAlreadyInUseException extends Exception {
	public UsernameAlreadyInUseException(){
        super("This username is already in use by other person");
    }
}
