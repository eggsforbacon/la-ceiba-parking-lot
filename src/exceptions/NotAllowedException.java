package exceptions;

public class NotAllowedException extends Exception {
    public NotAllowedException(){
        super("This type of vehicle cannot go in this spot");
    }

}
