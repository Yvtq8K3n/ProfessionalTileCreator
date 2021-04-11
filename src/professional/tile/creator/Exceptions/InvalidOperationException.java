package professional.tile.creator.Exceptions;

public class InvalidOperationException extends Exception {

    public InvalidOperationException() {
        super("INVALID OPERATION");
    }

    public InvalidOperationException(String message) {
        super(message+": INVALID OPERATION");
    }
}