package com.addisuthomas.bank_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MAuthenticationProvider authProvider;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final String[] AUTH_WHITELIST = { "/card/validateCardNumber/**","/card/chooseAuthMethod/**", 
			"/swagger-ui/**", "/v3/api-docs/**",
			"/swagger-resources", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**" };

	public WebSecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers(HttpMethod.POST, "/cachedemo/v1/users/signup").permitAll().anyRequest().authenticated()
				.and().addFilter(new AuthenticationFilter(authenticationManager()))
				.addFilter(new AuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(authProvider);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
