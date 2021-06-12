package exceptions;

/**
 * Exception thrown when the ID is already used by some other element of the model. <br>
 * */
public class IDAlreadyInUseException extends Exception{
    /**
     * This is the main constructor of the class. <br>
     * */
	public IDAlreadyInUseException(){
        super("This ID is already in use by other person");
    }
}
