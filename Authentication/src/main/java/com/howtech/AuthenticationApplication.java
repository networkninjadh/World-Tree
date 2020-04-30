package com.howtech;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.howtech.model.Role;
import com.howtech.model.User;
import com.howtech.service.UserService;

@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationApplication(UserService userService){
		this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void run(String... params) throws Exception {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setRoles(new ArrayList<Role> (Arrays.asList(Role.ROLE_ADMIN)));
		
		userService.signup(admin);
		
		User client = new User();
		client.setUsername("client");
		client.setPassword("client");
		client.setEmail("client@email.com");
		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
		
		userService.signup(client);
	}
}
