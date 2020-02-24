package com.algaworks.brewer.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	
	protected void beforeSpringSecurityFilterChain(javax.servlet.ServletContext servletContext) {
		
		//servletContext.getSessionCookieConfig().setMaxAge(20); 							//tempo p expirar mexendo ou nao
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE)); 	//não enviar o session ID via url
		
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter",
				new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	};
}
