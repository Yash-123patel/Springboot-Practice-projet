package com.tekworks.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@PropertySource("users.properties")
public class AppConfig {
	
	@Value("${first.user.name}")
	private String firstUsername;
	
	@Value("${first.user.password}")
	private String firstUserpassword;
	
	@Value("${first.user.role}")
	private String firstUserRole;
	
	@Value("${second.user.name}")
	private String secondUsername;
	
	@Value("${second.user.password}")
	private String secondUserpassword;
	
	@Value("${second.user.role}")
	private String secondUserRole;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.builder()
				                .username(firstUsername)
				                .password(encoder.encode(firstUserpassword))
				                .roles(firstUserRole)
				                .build();

		UserDetails norm = User.builder()
				               .username(secondUsername)
				               .password(encoder.encode(secondUserpassword))
				               .roles(secondUserRole)
				               .build();

		return new InMemoryUserDetailsManager(admin,norm);
	}
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		http.csrf(a->a.disable())
		.authorizeRequests(authorize->{
			authorize.requestMatchers("/searchByName").permitAll();
			authorize.anyRequest().authenticated();
			})
		.formLogin(Customizer.withDefaults());
		return http.build();
	}
}
