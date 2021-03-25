package ErrorHandling;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String error) {
        super(error);
    }
}