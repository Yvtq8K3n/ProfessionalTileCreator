package professional.tile.creator.exceptions;

public class OutOfBoundsException extends Exception {

    public OutOfBoundsException() {
        super("OUT OF BOUNDS");
    }

    public OutOfBoundsException(String message) {
        super(message+" is out of bounds");
    }
}
