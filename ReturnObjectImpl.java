/**
 * A wrapper containing either an object (the result of an operation
 * on a data structure) or an error value.
 *
 * @author Jacob Williams
 */
public class ReturnObjectImpl implements ReturnObject {
    ErrorMessage theErrorMessage = ErrorMessage.NO_ERROR;
    Object theObject = null;
    public ReturnObjectImpl(ErrorMessage theErrorMessage ) {
        this.theErrorMessage = theErrorMessage;
    }
    public ReturnObjectImpl(Object theObject) {
	this.theObject = theObject;
    }


    /**
     * Returns whether there has been an error
     * @return whether there has been an error
     */
    public boolean hasError() {
	if (theErrorMessage == ErrorMessage.NO_ERROR) {
	    return false;
	}
	else return true;
    }

    /**
     * Returns the error message.
     *
     * This method must return NO_ERROR if and only if
     * {@hasError} returns false.
     *
     * @return the error message
     */
    public ErrorMessage getError() {
	return theErrorMessage;
    }

    /**
     * Returns the object wrapped in this ReturnObject, i.e. the
     * result of the operation if it was successful, or null if
     * there has been an error.
     *
     * Note that the output of this method must be null if {@see
     * hasError} returns true, but the opposite is not true: if
     * {@see hasError} returns false, this method may or may not
     * return null.
     *
     * @return the return value from the method or null if there has been an error
     */
    public Object getReturnValue() {
	return theObject;
    }
}
