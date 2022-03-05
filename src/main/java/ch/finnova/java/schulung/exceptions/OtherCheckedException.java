package ch.finnova.java.schulung.exceptions;

public class OtherCheckedException extends Exception {
    public OtherCheckedException() {}

    public OtherCheckedException(String aMessage) {
        super(aMessage);
    }

    public OtherCheckedException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }
}
