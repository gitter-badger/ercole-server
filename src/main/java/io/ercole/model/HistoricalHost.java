package io.ercole.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

/**
 * Object mapped with JPA in database.
 */
@Entity
public class HistoricalHost extends Host {

	/**
	 * Instantiates a new historical host.
	 */
	public HistoricalHost() {
		super();
	}
	

	/**
	 * @param id 
	 * @param hostname 
	 * @param environment 
	 * @param location 
	 * @param usetype The usetype
	 * @param databases 
	 * @param schemas 
	 * @param extraInfo 
	 * @param associatedClusterName associated cluster name
	 * @param hostInfo 
	 * @param updated 
	 */
	public HistoricalHost(final Long id, final @NotEmpty String hostname, final String environment,
			final String location, final String usetype,
			final String databases, final String schemas, final String extraInfo, 
			final String associatedClusterName, final String hostInfo, final Date updated) {
		super(id, hostname, environment, location, usetype, databases, schemas, extraInfo,
				associatedClusterName, hostInfo, updated);
		this.archived = new Date();
	}

	private Date archived;

	/**
	 * Gets the archived.
	 *
	 * @return the archived
	 */
	public Date getArchived() {
		return archived;
	}

	/**
	 * Sets the archived.
	 *
	 * @param archived the new archived
	 */
	public void setArchived(final Date archived) {
		this.archived = archived;
	}

}
