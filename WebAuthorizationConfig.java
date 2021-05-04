package com.nagarro.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nagarro.auth.filter.RequestValidationFilter;


@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic();
		http.sessionManagement().maximumSessions(1);
		http.addFilterAfter(new RequestValidationFilter(),
				BasicAuthenticationFilter.class).authorizeRequests().mvcMatchers( "/api/account").hasAnyRole("ADMIN", "USER");
	}
}
