package com.nagarro.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		var manager = new InMemoryUserDetailsManager();
		var user1 = User.withUsername("admin")
						.password("$2y$12$D1TxVdBY5mTyqypnzb41BOnrcE6z5StLkNkTkw8IcteRrF/Xj8sPm")
						.roles("ADMIN")
						.build();
		var user2 = User.withUsername("user")
						.password("$2y$12$Wm5KodKwItwps0WqbFMJh.PnVxhS3wta0LYN2GKXe8RyHHLQ/Tx9e")
						.roles("USER")
						.build();	
		manager.createUser(user1);
		manager.createUser(user2);
		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
