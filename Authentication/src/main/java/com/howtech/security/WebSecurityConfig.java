package com.howtech.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	//TODO: implement session handling
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// Disable CSRF (cross site request forgery)
	    http.csrf().disable();

	    // No session will be created or used by spring security
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	    // Entry points
	    http.authorizeRequests()
	        .antMatchers("/users/signin").permitAll()
	        .antMatchers("/users/signup").permitAll()
	        .antMatchers("/h2-console/**/**").permitAll()
	        .antMatchers("/v2/api-docs").permitAll()
	        .antMatchers("/configuration").permitAll()
	        .antMatchers("/swagger-resources/*").permitAll()
	        .antMatchers("/swagger-ui.html").permitAll()
	        .antMatchers("/webjars/**").permitAll();

	    http.exceptionHandling().accessDeniedPage("/login");

	    // Apply JWT
	    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

	   // super.configure(http);
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
}
