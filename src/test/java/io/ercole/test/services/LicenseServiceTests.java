package io.ercole.test.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import io.ercole.model.License;
import io.ercole.repositories.LicenseRepository;
import io.ercole.services.LicenseService;

@RunWith(PowerMockRunner.class)
public class LicenseServiceTests {

	@Mock
	private LicenseRepository licenseRepo;
	
	@InjectMocks
	private LicenseService licService;
	
	@Test
	public void updateLicenses() {
		List<License> licenses = new ArrayList<>();
		
		License license1 = new License();
		license1.setId("Web");
		license1.setLicenseCount(2l);
		License license2 = new License();
		license2.setId("Logic");
		license1.setLicenseCount(10l);
		licenses.add(license1);
		licenses.add(license2);
		
		List<License> repoList = new ArrayList<>();
		License license3 = new License();
		license3.setId("Web");
		license3.setLicenseCount(2l);
		repoList.add(license3);
		
		when(licenseRepo.findAll()).thenReturn(repoList);
		when(licenseRepo.saveAll(repoList)).thenReturn(repoList);
		
		assertEquals(repoList, licService.updateLicenses(licenses));
	}

}
