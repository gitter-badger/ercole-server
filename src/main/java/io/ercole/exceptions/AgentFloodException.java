package io.ercole.exceptions;

/**
 * Exception for update flooding from agents.
 */
public class AgentFloodException extends Exception {

	private static final long serialVersionUID = -6645825203420091269L;

	/**
	 * @param message to be thrown
	 */
	public AgentFloodException(final String message) {
		super(message);
	}

}
