package ch.finnova.java.schulung.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String aMessage) {
        super(aMessage);
    }
}
