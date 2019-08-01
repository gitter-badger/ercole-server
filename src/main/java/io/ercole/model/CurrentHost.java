package io.ercole.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

/**
 * Object mapped with JPA in database.
 */

@Entity
public class CurrentHost extends Host {

	/**
	 * Instantiates a new current host.
	 */
	public CurrentHost() {
		super();
	}
	
	/**
	 * @param id 
	 * @param hostname 
	 * @param environment 
	 * @param location 
	 * @param hostType The HostType
	 * @param databases 
	 * @param schemas 
	 * @param extraInfo 
	 * @param associatedClusterName associated cluster name
	 * @param hostInfo 
	 * @param updated 
	 */
	public CurrentHost(final Long id, final @NotEmpty String hostname, final String environment,
			final String location, final String hostType,
			final String databases, final String schemas, final String extraInfo,
			final String associatedClusterName, final String hostInfo, final Date updated) {
		super(id, hostname, environment, location, hostType, databases, schemas,
				extraInfo, associatedClusterName, hostInfo, updated);
	}
}
