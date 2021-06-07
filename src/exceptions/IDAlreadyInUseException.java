package exceptions;

public class IDAlreadyInUseException extends Exception{
	public IDAlreadyInUseException(){
        super("This ID is already in use by other person");
    }
}
