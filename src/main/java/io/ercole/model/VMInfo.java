package io.ercole.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * It contain info about a VM.
 */
@Entity
public class VMInfo {
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    //@Column(unique = true)
    private String name;
    private String clusterName;
    //@Column(unique = true)
    private String hostName;

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to be set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    /**
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * @return the cluster name
     */
    public String getClusterName() {
        return this.clusterName;
    }
    /**
     * @param clusterName the cluster name
     */
    public void setClusterName(final String clusterName) {
        this.clusterName = clusterName;
    }
    /**
     * @return the hostname
     */
    public String getHostName() {
        return this.hostName;
    }
    /**
     * @param hostName the hostname
     */
    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }
    /**
     * Create a new VMInfo.
     * @param id the id
     * @param name the name
     * @param clusterName the cluster name
     * @param hostName the hostname
     */
    public VMInfo(final Long id, final String name, final String clusterName, final String hostName) {
        this.id = id;
        this.name = name;
        this.clusterName = clusterName;
        this.hostName = hostName;
    }
    /**
     * Create a new VMInfo.
     */
    public VMInfo() {
    }
}
