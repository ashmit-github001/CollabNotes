package com.ashmitagarwal.collabnotes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ashmitagarwal.collabnotes.user.service.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(customizer -> customizer
				.requestMatchers(HttpMethod.POST, "/user/register").permitAll()
				.anyRequest().authenticated())
		.formLogin(login -> login
				.loginPage("http://127.0.0.1:5500")
				.loginProcessingUrl("/login"))
		.csrf(customizer -> customizer.disable())
		.cors(Customizer.withDefaults());
		
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500", 
	    		"http://localhost:5500"));
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowedHeaders(List.of("*"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
