package ch.finnova.java.schulung.exceptions;

/**
 * This class illustrates handing of checked vs un-checked exceptions.
 */
public class ExceptionUser {

    /**
     * This method throws a checked exception.
     * @throws OtherCheckedException
     */
    public void throwACheckedException() throws OtherCheckedException {
        throw new OtherCheckedException();
    }

    /**
     * This method throws an un-checked exception.
     * @throws OtherUncheckedException
     */
    public void throwAnUncheckedException() throws OtherUncheckedException {
        throw new OtherUncheckedException();
    }

    /**
     * This method calls a method that throws a checked exception, but does not handle it:
     * It must delegate it to the caller.
     * @throws OtherCheckedException
     */
    public void useCheckedExceptionAndRetrhow() throws OtherCheckedException {
        throwACheckedException();
    }

    /**
     * This method calls a method that throws a checked exception and handles it.
     */
    public void useCheckedExceptionAndHandle() {
        try {
            throwACheckedException();
        } catch (OtherCheckedException e) {
            // hanlde it
        }
    }

    /**
     * This method calls a method that throws an unchecked exception; special handling is not necessary, but possible.
     */
    public void useUncheckeException() {
        throwAnUncheckedException();
    }

    /**
     * This method handles an exception and handles it.
     */
    public void handleOneException() {
        try {
            // some code
        } catch (Exception e) {
            // handle this exception
        } finally {
            // some code that is always executed
        }
    }

    /**
     * This mehtod handles several exceptions individually.
     */
    public void handleSeveralExceptionIndividually() {
        try {
            // some code
        } catch (NullPointerException e) {
            // handle this exception
        } catch (NotFoundException e) {
            // handle this exception
        } finally {
            // some code that is always executed
        }
    }

    /**
     * This method handles several exceptions in one go.
     */
    public void handleSeveralExceptionTogether() {
        try {
            // some code
        } catch (NullPointerException | NotFoundException e) {
            // handle these exceptions in the same manner
        } finally {
            // some code that is always executed
        }
    }
}
