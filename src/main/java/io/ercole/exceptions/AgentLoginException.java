package io.ercole.exceptions;

/**
 * Exception for login problems with the agents.
 */
public class AgentLoginException extends Exception {

	private static final long serialVersionUID = 7742041245065496809L;

	/**
	 * @param msg to throw
	 */
	public AgentLoginException(final String msg) {
		super(msg);
	}
}
