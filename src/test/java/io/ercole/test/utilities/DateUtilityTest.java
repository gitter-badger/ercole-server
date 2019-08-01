package io.ercole.test.utilities;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import io.ercole.utilities.DateUtility;

public class DateUtilityTest {
	
	@Test
	public void betweenTest() {
		Date comparedDate = new Date(2l);
		Date first = new Date(1l);
		Date second = new Date(3l);
		assertTrue(DateUtility.between(comparedDate, first, second));
		
		comparedDate = new Date(4l);
		assertFalse(DateUtility.between(comparedDate, first, second));
	}
	
	
	@Test
	public void fromStringToDateTest() throws ParseException {
		String correctDate = "2018-01-01";
		Date result = DateUtility.fromStringToDate(correctDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("01/01/2018");
		
		assertEquals(result, d);
	}
	
	@Test
	public void isValidUpdateRangeTest() throws ParseException {
		Date now = new Date();
		assertFalse(DateUtility.isValidUpdateRange(now, 1000000000));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date past = sdf.parse("01/01/2018");
		assertTrue(DateUtility.isValidUpdateRange(past, 10));	
	}

}
