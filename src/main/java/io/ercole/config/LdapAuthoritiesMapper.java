package io.ercole.config;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.stereotype.Component;

/**
 * LDAP Authorities mapper.
 * 
 */
@Component
public class LdapAuthoritiesMapper implements GrantedAuthoritiesMapper {
	private static final Log LOGGER = LogFactory.getLog(LdapAuthoritiesMapper.class);

	@Value("${auth.ad.role}")
	private String adRole;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
	 * #mapAuthorities(java.util.Collection)
	 */
	@Override
	public Collection<LdapAuthority> mapAuthorities(final Collection<? extends GrantedAuthority> authorities) {
		Set<LdapAuthority> roles = EnumSet.noneOf(LdapAuthority.class); // empty EnumSet
		for (GrantedAuthority authority : authorities) {
			LOGGER.error(authority.getAuthority());
			if (adRole.equalsIgnoreCase(authority.getAuthority())) {
				roles.add(LdapAuthority.ROLE_USER);
			}
		}
		return roles;
	}

}
