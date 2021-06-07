package exceptions;

public class PasswordAlreadyInUseException extends Exception {
	public PasswordAlreadyInUseException(){
        super("This password is already in use by other person");
    }
}
