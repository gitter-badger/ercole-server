package io.ercole.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import io.ercole.model.ClusterInfo;
import io.ercole.model.VMInfo;

/**
 * Repository for clusters.
 */
public interface ClusterRepository extends PagingAndSortingRepository<ClusterInfo, Long> {

    /**
     * Remove the cluster by name.
     *
     * @param name name of the cluster
     */
    void deleteByName(String name);

    /**
     * Find a cluster by name.
     *
     * @param name name of the cluster
     * @return the cluster info
     */
    ClusterInfo findByName(String name);

    /**
     * Find a VM containing the hostname.
     * @param name name
     * @return the vminfo
     */
    @Query("select vi from VMInfo vi where vi.hostName = :#{#name}")
    VMInfo findOneVMInfoByHostname(@Param("name") String name);

    /**
     * Return all cluster that match the filter.
     * @param filter filter
     * @return all cluster that match the filter
     */
    @Query("SELECT cl from ClusterInfo cl WHERE LOWER(cl.name) LIKE LOWER (CONCAT('%',:filter,'%'))")
    List<ClusterInfo> getClusters(String filter);
}
