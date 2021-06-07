package exceptions;

public class NameAlreadyInUseException extends Exception{
	public NameAlreadyInUseException(){
        super("This name is already in use by other person");
    }
}
