package io.ercole.repositories;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.ercole.model.Alert;
import io.ercole.model.AlertCode;
import io.ercole.model.AlertSeverity;

/**
 * Repository for Alert generation.
 */
public interface AlertRepository extends PagingAndSortingRepository<Alert, Long> {
	
	/**
	 * Set new from ack.
	 * 
	 * @param id of Alert object to update
	 * @return Alert object set from new to ack "status"
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Alert a SET a.status = 'ACK' WHERE a.id = :id")
	int setFromNewToAck(@Param("id") Long id);
	

	/**
	 * Find NEW.
	 * @param severity severity
	 * @param search search text
	 * @param startdate start date
	 * @param enddate end date
	 * @param c page
	 * @return a list of new alerts.
	 */
	@Query(value = "SELECT m FROM Alert m WHERE m.status = 'NEW' " 
		+ "AND (:severity IS NULL OR :severity = m.severity) "
		+ "AND (:search IS NULL OR m.hostname LIKE %:search% OR m.description LIKE %:search%) "
		+ "AND (CAST(:startdate AS timestamp) is NULL OR CAST(m.date AS timestamp) >= :startdate) "
		+ "AND (CAST(:enddate AS timestamp) IS NULL OR CAST(m.date AS timestamp) <= :enddate) ")
	Page<Alert> findNEW(
			@Param("severity") AlertSeverity severity,
			@Param("search") String search,
			@DateTimeFormat(iso = ISO.DATE) @Param("startdate") Date startdate,
			@DateTimeFormat(iso = ISO.DATE) @Param("enddate") Date enddate, Pageable c);
	/**
	 * Find ack'd alerts by host name.
	 * 
	 * @param hostname to get Alerts for
	 * @param c 
	 * @return a list of ACK Alerts relative to the given hostname
	 */
	@Query("SELECT m FROM Alert m WHERE m.status = 'ACK' AND m.hostname = :hostname "
			+ "ORDER BY m.date DESC")
	Page<Alert> findACKByHostname(@Param("hostname") String hostname, Pageable c);
	
	/**
	 * Returns alerts for a hostname.
	 * @param hostname the host name
	 * @param c the paging informations
	 * @return a list of alerts for the host
	 */
	Page<Alert> findByHostname(@Param("hostname") String hostname, Pageable c);
	
	/**
	 * Find new alerts by host name.
	 * 
	 * @param hostname to get Alerts for
	 * @param c 
	 * @return a list of NEW Alerts relative to the given hostname
	 */
	@Query("SELECT m FROM Alert m WHERE m.status = 'NEW' AND m.hostname = :hostname "
			+ "ORDER BY m.date DESC")
	Page<Alert> findNEWByHostname(@Param("hostname") String hostname, Pageable c);

	/**
	 * Find NEW.
	 * @param severity severity
	 * @param search search text
	 * @param startdate start date
	 * @param enddate end date
	 * @param c page
	 * @return a list of new alerts.
	 */
	@Query(value = "SELECT m FROM Alert m WHERE (:severity IS NULL OR :severity = m.severity) "
		+ "AND (:search IS NULL OR m.hostname LIKE %:search% OR m.description LIKE %:search%) "
		+ "AND (CAST(:startdate AS timestamp) is NULL OR CAST(m.date AS timestamp) >= :startdate) "
		+ "AND (CAST(:enddate AS timestamp) IS NULL OR CAST(m.date AS timestamp) <= :enddate) ")
	Page<Alert> findAll(
			@Param("severity") AlertSeverity severity,
			@Param("search") String search,
			@DateTimeFormat(iso = ISO.DATE) @Param("startdate") Date startdate,
			@DateTimeFormat(iso = ISO.DATE) @Param("enddate") Date enddate, Pageable c);

	/**
	 * Return true when a alert with a certain hostname and code exist.
	 * @param hostname hostname
	 * @param code alert code
	 * @return boolean value
	 */
	boolean existsByHostnameAndCode(final String hostname, final AlertCode code);
}
