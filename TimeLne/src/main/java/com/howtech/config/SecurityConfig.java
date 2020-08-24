 package com.howtech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.howtech.security.ApplicationUserRole;
import com.howtech.security.JwtRequestFilter;
import com.howtech.security.ApplicationUserPermission;
import com.howtech.security.JwtAuthenticationEntryPoint;

/**
 * 
 * @author Damond Howard
 * @apiNote Entire security policy configuration for the application this secures all routes behind certain user permissions
 */
@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/**
	 * 
	 * @param passwordEncoder @autowired passwordEncoder from our bean configuration
	 */
	public SecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
	}
	
	/**
	 * @param http 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(
					"/", 
					"/auth-api/signin",
					"/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**" )
			.permitAll()
			//.antMatchers("/store-api/**").hasRole(ApplicationUserRole.OWNER.name())
			//.antMatchers(HttpMethod.DELETE, "/store-api/**").hasAuthority(ApplicationUserPermission.STORE_WRITE.name())
			//.antMatchers(HttpMethod.POST, "/store-api/**").hasAuthority(ApplicationUserPermission.STORE_WRITE.name())
			//.antMatchers(HttpMethod.PUT, "/store-api/**").hasAuthority(ApplicationUserPermission.STORE_WRITE.name())
			//.antMatchers(HttpMethod.GET, "/store-api/**").hasAuthority(ApplicationUserPermission.STORE_READ.name())
			//.antMatchers("/customer-api/**").hasRole(ApplicationUserRole.CUSTOMER.name())
			//TODO: the rest of the apis will be secured here based off the user permissions
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			//.apply(new JwtConfigurer(jwtTokenProvider));
			//.httpBasic();
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails ownerUser = User.builder()
			.username("annasmith")
			.password(passwordEncoder.encode("password"))
			//.roles(ApplicationUserRole.OWNER.name())
			.authorities(ApplicationUserRole.OWNER.getGrantedAuthorities()) //TODO:
			.build();
		
		UserDetails employeeUser = User.builder()
			.username("linda")
			.password(passwordEncoder.encode("password"))
			//.roles(ApplicationUserRole.EMPLOYEE.name())
			.authorities(ApplicationUserRole.EMPLOYEE.getGrantedAuthorities())
			.build();
		
		UserDetails customerUser = User.builder()
				.username("networkninjadh")
				.password(passwordEncoder.encode("password"))
				//.roles(ApplicationUserRole.CUSTOMER.name())
				.authorities(ApplicationUserRole.CUSTOMER.getGrantedAuthorities())
				.build();
		
		return new InMemoryUserDetailsManager(
				ownerUser,
				employeeUser,
				customerUser
				);
	}
}