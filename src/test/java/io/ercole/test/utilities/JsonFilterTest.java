package io.ercole.test.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import io.ercole.model.CurrentHost;
import io.ercole.utilities.JsonFilter;

@RunWith(PowerMockRunner.class)
public class JsonFilterTest {
	JSONArray array = new JSONArray("[{\"Name\":\"db1\",\"Features\":["
			+"{\"Name\":\"Logic\",\"Status\":true},"
			+ "{\"Name\":\"Prova\",\"Status\":false}]}]");

	@Test
	public void getFeaturesMappingTest() {
		Map<String, Map<String, Boolean>> retMap = new HashMap<>();
		
		Map<String, Boolean> featureMap = new HashMap<>();
		featureMap.put("Logic", true);
		featureMap.put("Prova", false);
		retMap.put("db1", featureMap);
		
		assertEquals(retMap, JsonFilter.getFeaturesMapping(array));
	}
	
	@Test
	public void getTrueFeaturesFromDbArrayTest() {	
		Map<String, Boolean> featureMap = new HashMap<>();
		featureMap.put("Logic", true);

		
		assertEquals(featureMap, JsonFilter.getTrueFeaturesFromDbArray(array));
	}
	
	
	@Test
	public void getFalseFeaturesFromDbArrayTest() {	
		Map<String, Boolean> featureMap = new HashMap<>();
		featureMap.put("Prova", false);

		
		assertEquals(featureMap, JsonFilter.getFalseFeaturesFromDbArray(array));
	}

	
	@Test
	public void buildCurrentHostFromJSON() {
		JSONObject obj = new JSONObject("{" 
				+ "\"Hostname\": \"testing\","
				+ "\"Environment\": \"TST\","
				+ "\"Location\": \"Italy\", " 
				+ "\"Databases\": \"pippo Pluto\","
				+ "\"Schemas\": \"REMOTE_SCHEDULER_AGENT SYS$UMF GGSYS DBSFWUSER SYSRAC REMOTE_SCHEDULER_AGENT SYS$UMF GGSYSDBSFWUSER SYSRAC\","
				+ "\"Info\": {},"
				+ "\"Extra\": {"
				+ "\"Databases\": [{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\"}]}}");
		
		Date data = new Date(1l);
		CurrentHost host = new CurrentHost(1l, "testing", "TST", "Italy", "oracledb", "pippo Pluto", "REMOTE_SCHEDULER_AGENT "
				+ "SYS$UMF GGSYS DBSFWUSER SYSRAC REMOTE_SCHEDULER_AGENT SYS$UMF GGSYSDBSFWUSER SYSRAC", "{"
						+ "\"Databases\": [{\"Name\": \"db1\","
						+ "\"UniqueName\": \"db1\","
						+ "\"Status\": \"OPEN\"}]}",null, "{}", data);
		assertEquals(host.getHostname(), JsonFilter.buildCurrentHostFromJSON(obj).getHostname());
	}
	
	@Test
	public void getNewDatabasesTest() {
		CurrentHost oldHost = new CurrentHost();
		oldHost.setDatabases("TST CCC");
		oldHost.setId(2l);
		
		CurrentHost newHost = new CurrentHost();
		newHost.setDatabases("TST BRB");
		newHost.setId(1l);
		
		
		List<String> result = JsonFilter.getNewDatabases(newHost, oldHost);
		
		assertTrue(result.contains("BRB"));
		assertTrue(result.size() == 1);
		assertEquals("BRB", result.get(0));
		
		newHost.setDatabases("TST BRB AAA");
		result = JsonFilter.getNewDatabases(newHost, oldHost);
		assertTrue(result.contains("AAA") && result.contains("BRB"));
	}
	
	@Test
	public void getDatabasesTest() {
		CurrentHost host = new CurrentHost();
		host.setDatabases("TST CCC");
		host.setId(2l);
		
		List<String> result = JsonFilter.getDatabases(host);
		assertTrue(result.size() == 2);
		assertTrue(result.contains("TST") && result.contains("CCC"));
	}
	
	@Test
	public void getSameDatabasesTest() {
		CurrentHost oldHost = new CurrentHost();
		oldHost.setDatabases("TST CCC");
		oldHost.setId(2l);
		
		CurrentHost newHost = new CurrentHost();
		newHost.setDatabases("TST BRB");
		newHost.setId(1l);
		
		List<String> result = JsonFilter.getSameDatabases(newHost, oldHost);
		
		assertTrue(result.contains("TST"));
		assertTrue(result.size() == 1);
	}
	
	
	@Test
	public void hasEnterpriseLicensesTest() {
		JSONArray array = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle ENT\",\"Count\":1}]}]"); 
		assertEquals(true, JsonFilter.hasEnterpriseLicenses(array));
		
		JSONArray array2 = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle EXT\",\"Count\":1}]}]");
		assertEquals(true, JsonFilter.hasEnterpriseLicenses(array2));
		
		JSONArray array3 = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle STD\",\"Count\":1}]}]");
		assertEquals(false, JsonFilter.hasEnterpriseLicenses(array3));
		
		
		JSONArray array4 = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle EXT\",\"Count\":0}]}]");
		assertEquals(false, JsonFilter.hasEnterpriseLicenses(array4));
	}
	
	
	@Test
	public void hasNewEnterpriseLicenses() {
		JSONArray arrayOld = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle STD\",\"Count\":1}]}]"); 
		JSONArray arrayNew = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle EXT\",\"Count\":1}]}]");
		assertEquals(true, JsonFilter.hasNewEnterpriseLicenses(arrayOld, arrayNew));
		
		arrayNew = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle EXT\",\"Count\":0}]}]");
		assertEquals(false, JsonFilter.hasNewEnterpriseLicenses(arrayOld, arrayNew));
		
		arrayOld = new JSONArray("[{"
				+ "\"Name\": \"db1\","
				+ "\"UniqueName\": \"db1\","
				+ "\"Status\": \"OPEN\","
				+ "\"Licenses\":[{\"Name\":\"Oracle EXT\",\"Count\":1}]}]");
		assertEquals(false, JsonFilter.hasNewEnterpriseLicenses(arrayOld, arrayNew));
	}
	
	@Test
	public void hasMoreCPUCoresTest() {
		CurrentHost oldHost = new CurrentHost();
		oldHost.setHostInfo("{\"CPUCores\":2}");
		
		CurrentHost newHost = new CurrentHost();
		newHost.setHostInfo("{\"CPUCores\":4}");
		
		assertEquals(true, JsonFilter.hasMoreCPUCores(oldHost, newHost));
		
		oldHost.setHostInfo("{\"CPUCores\":4}");
		assertEquals(false, JsonFilter.hasMoreCPUCores(oldHost, newHost));
	}
}
