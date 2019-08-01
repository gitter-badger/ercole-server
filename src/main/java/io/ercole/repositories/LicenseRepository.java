package io.ercole.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.ercole.model.License;

/**
 * Repository for Licenses.
 */
@RepositoryRestResource
public interface LicenseRepository extends CrudRepository<License, Long> {
	
	
}
