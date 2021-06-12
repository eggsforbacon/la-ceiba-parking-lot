package exceptions;

/**
 * Exception thrown when the spot is already in use. <br>
 * */
public class NotAllowedException extends Exception {

    /**
     * This is the main constructor of the class. <br>
     * */
    public NotAllowedException(){
        super("This type of vehicle cannot go in this spot");
    }

}
