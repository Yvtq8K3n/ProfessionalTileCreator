package professional.tile.creator.Exceptions;

public class OutOfBoundsException extends Exception {

    public OutOfBoundsException() {
        super("OUT OF BOUNDS");
    }

    public OutOfBoundsException(String message) {
        super(message+": OUT OF BOUNDS");
    }
}
