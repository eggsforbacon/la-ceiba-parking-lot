package exceptions;

/**
 * Exception thrown when the name is already used by some other element of the model. <br>
 * */
public class NameAlreadyInUseException extends Exception{

    /**
     * This is the main constructor of the class. <br>
     * */
	public NameAlreadyInUseException(){
        super("This name is already in use by other person");
    }
}
