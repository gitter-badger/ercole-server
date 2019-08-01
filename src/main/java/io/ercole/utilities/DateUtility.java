package io.ercole.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Date utility class.
 */
public final class DateUtility {
	
	private DateUtility() {
		
	}
	
	/**
	 * @param comparedDate 
	 * @param first 
	 * @param second 
	 * @return true/false depending if comparedDate is between first and second
	 */
	public static boolean between(final Date comparedDate, final Date first, final Date second) {
		return comparedDate.before(second) && comparedDate.after(first);
	}
	
	/**
	 * @param data 
	 * 				String in "dd-MM-yyyy" format
	 * @return Date object
	 * @throws ParseException 
	 */
	public static Date fromStringToDate(final String data) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(data);	 
	}
	
	
	/**
	 * @param updated
	 * 			param to sum to the agent.update.rate is
	 * @param updateRate
	 * 			the rate injected from properties
	 * @return
	 * 			true if the sum is before NOW, false otherwise
	 */
	public static boolean isValidUpdateRange(final Date updated, final int updateRate) {
		return DateUtils.addMilliseconds(updated, updateRate).before(new Date());
	}
	
}
