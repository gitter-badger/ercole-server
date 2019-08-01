package io.ercole.model;
/**
 * The Enum AlertCode.
 */
public enum AlertCode {
	
	/** The new database. */
	NEW_DATABASE("New Database"), 
	
	/** The new option. */
	NEW_OPTION("New Option"), 
	
	/** The new license. */
	NEW_LICENSE("New License"), 
	
	/** The new server. */
	NEW_SERVER("New Server"),
	
	/** The no data from agent. */
	NO_DATA("No Data");

	/**
	 * Instantiates a new alert code.
	 *
	 * @param title the title
	 */
	AlertCode(final String title) {
		this.briefDescr = title;
	}
	
	/** The brief descr. */
	private final String briefDescr;
	
	/**
	 * Gets the brief descr.
	 *
	 * @return the brief descr
	 */
	public String getBriefDescr() {
		return briefDescr;
	}
	
}
