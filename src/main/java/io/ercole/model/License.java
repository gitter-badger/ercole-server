package io.ercole.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The Class Alert.
 */
@Entity
public class License {


	/** The id. */
	@Id
	private String id;

	/** The number of owned licenses. */
	private Long licenseCount;

	/**
	 * Instantiates a new license.
	 */
	public License() {
		// used for testing and JPA purposes
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Gets the number of owned licenses.
	 *
	 * @return the license count
	 */
	public Long getLicenseCount() {
		return licenseCount;
	}

	/**
	 * Sets the number of owned licenses.
	 *
	 * @param licenseCount the new license count
	 */
	public void setLicenseCount(final Long licenseCount) {
		this.licenseCount = licenseCount;
	}
	
	

}
