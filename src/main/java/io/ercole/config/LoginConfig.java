package io.ercole.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Login path specified.
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

	/**
	 *  @param registry is the Registration of default pages
	 */
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("auth/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
