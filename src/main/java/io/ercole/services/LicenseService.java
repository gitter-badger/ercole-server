package io.ercole.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ercole.model.License;
import io.ercole.repositories.LicenseRepository;

/**
 * Service component for License.
 */
@Service
public class LicenseService {

	@Autowired
	private LicenseRepository licenseRepo;

	/**
	 * Update licenses.
	 * 
	 * @param licenses
	 *            the list of licenses to update
	 * 
	 * @return a list of updated licenses
	 */
	public Iterable<License> updateLicenses(final List<License> licenses) {
		Iterable<License> repoLicenses = licenseRepo.findAll();
		for (License repoLicense : repoLicenses) {
			for (License license : licenses) {
				if (license.getId().equals(repoLicense.getId())) {
					repoLicense.setLicenseCount(license.getLicenseCount());
				}
			}
		}
		return licenseRepo.saveAll(repoLicenses);
	}

}
