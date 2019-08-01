package io.ercole.exceptions;

/**
 * Exception to be thrown when we don't have any historicalHost related to the searched hostname.
 */
public class NoHistoryFoundException extends Exception {

	private static final long serialVersionUID = -3038769143800085998L;

	/**
	 * @param msg to be thrown
	 */
	public NoHistoryFoundException(final String msg) {
		super(msg);
	}

}
