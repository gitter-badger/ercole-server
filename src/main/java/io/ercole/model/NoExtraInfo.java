package io.ercole.model;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection for not visualizing extraInfo attribute of CurrentHost objects.
 *
 */
@Projection(name = "noExtraInfo", types = { CurrentHost.class })
public interface NoExtraInfo {
	
	/**
	 * @return hostname
	 */
	String getHostname();
	/**
	 * @return the environment
	 */
	String getEnvironment();
	/**
	 * @return the location
	 */
	String getLocation();
	/**
	 * @return the hostType
	 */
	String getHostType();

	/**
	 * @return the databases
	 */
	String getDatabases();
	
	/**
	 * @return the schemas
	 */
	String getSchemas();

	/**
	 * @return JSON infos regarding host OS and HW
	 */
	String getHostInfo();

}
