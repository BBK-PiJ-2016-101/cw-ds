public class ArrayList implements List {
    Object[] theArrayList = new Object[0];
    Object store;
    public boolean isEmpty() {
	if (theArrayList.length == 0) {
	    return true;
	}
        return false;
    }
    public int size() {
        return theArrayList.length;
    }
    public ReturnObject get(int index) {
	if ((index >= theArrayList.length) || (index < 0)) {
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }
        return new ReturnObjectImpl(theArrayList[index]);
    }
    public ReturnObject remove(int index) {
	ErrorMessage shift = ArrayListShift(index, -1);
	if (shift != ErrorMessage.NO_ERROR) {
	    return new ReturnObjectImpl(shift);
	} else {
            return new ReturnObjectImpl(store);
        }
    }
    public ReturnObject add(int index, Object item) {
	ErrorMessage shift = ArrayListShift(index, 1);
	if (shift != ErrorMessage.NO_ERROR) {
	    return new ReturnObjectImpl(shift);
	}
        else {
            theArrayList[index] = item;
            return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
        }
    }
    public ReturnObject add(Object item) {
        ErrorMessage shift = ArrayListShift(theArrayList.length, 1);
        if (shift != ErrorMessage.NO_ERROR) {
            return new ReturnObjectImpl(shift);
        }
        theArrayList[theArrayList.length - 1] = item;
	return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }

    // shift the elements of the array left/backwards or right/forward by a number of indices starting at index
    private ErrorMessage ArrayListShift(int index, int shift) {
        // except instead of trying to shift forward starting from a index beyond the right end
        if (index > theArrayList.length) {
	    return ErrorMessage.INDEX_OUT_OF_BOUNDS;
	}
        // except instead of trying to shift the index beyond the left start
        // or trying to shift the right most element beyond the left start
        // instead of trying to make a less than empty array
	else if (shift < 0 && ((shift * -1 > theArrayList.length) || (index > theArrayList.length - 1))) {
            return ErrorMessage.INDEX_OUT_OF_BOUNDS;
        }
        // this is the remove the last element case
        // just return the empty array
        else if (theArrayList.length + shift == -1) {
            Object theArrayList = new Object[0];
            return ErrorMessage.NO_ERROR;
        }
        // initialize the temporary copy
        Object[] shiftedArrayList = new Object[theArrayList.length + shift];
        // right shift elements to the right the index
        if (shift >= 0) {
	    for (int i = index; i + shift < shiftedArrayList.length; i++) {
                System.out.println("Shifting " + i + " to " + (i + shift));
		shiftedArrayList[i + shift] = theArrayList[i];
	    }
            // copy elements to the left of the inde
	    for (int i = 0 ; i < index; i++) {
		shiftedArrayList[i] = theArrayList[i];
	    }
            // set the index to null
	    for (int i = index; i < shift; i++) {
		shiftedArrayList[i] = null;
	    }
	}
	else {
            // save the index in this instance's store
            store = theArrayList[index];
            // left shift elements to  the left of the index
	    for (int i = index; i + shift >= 0; i--) {
		shiftedArrayList[i + shift] = theArrayList[i];
	    }
            // left shift elements to the right of the index
            for (int i = index; i < shiftedArrayList.length -1; i++) {
                shiftedArrayList[i] = theArrayList[i + 1];
            }
	}
        // replace this instance's array with the temporary array
	theArrayList = shiftedArrayList;
        // return no error
	return ErrorMessage.NO_ERROR;
    }
}
