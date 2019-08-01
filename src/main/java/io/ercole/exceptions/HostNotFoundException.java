package io.ercole.exceptions;

/**
 * Exception used for  not found host objects.
 */
public class HostNotFoundException extends Exception {

	private static final long serialVersionUID = 7424752322252403371L;

	/**
	 * @param msg to throw
	 */
	public HostNotFoundException(final String msg) {
		super(msg);
	}

}
