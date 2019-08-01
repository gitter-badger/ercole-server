package io.ercole.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * Maps LDAP Group application roles.
 */

public enum LdapAuthority implements GrantedAuthority {

	/** Standard user. */
	ROLE_USER;

	/**
	 * Get the authority.
	 * @return the authority
	 */
    public String getAuthority() {
        return name();
    }

}

