package io.ercole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ercole.model.License;
import io.ercole.services.LicenseService;

/**
 * Controller for License.
 */
@RestController
public class LicenseController {

	@Autowired
	private LicenseService licenseService;
	
	/**
	 * Update licenses.
	 * 
	 * @param licenses list of licenses to update.
	 * 
	 * @return list of updated Licenses
	 * 
	 */
	@PutMapping(value = "/updatelicenses", consumes = "application/json")
	public Iterable<License> updateLicenses(@RequestBody final List<License> licenses) {
		return licenseService.updateLicenses(licenses);
	}
	

}
