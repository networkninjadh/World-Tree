package com.howtech.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;

/**
 * 
 * @author Damond Howard
 * @apiNote this enum contains user roles
 *
 */
public enum ApplicationUserRole {
	EMPLOYEE(Sets.newHashSet()), //needs to be able to fill orders access inventory
	OWNER(Sets.newHashSet(ApplicationUserPermission.STORE_READ,ApplicationUserPermission.STORE_WRITE, ApplicationUserPermission.CUSTOMER_READ)), //can do everything with store and view customers
	CUSTOMER(Sets.newHashSet(ApplicationUserPermission.CUSTOMER_READ)),
	LAST_MIN_HAIR(Sets.newHashSet(ApplicationUserPermission.CUSTOMER_READ)); //needs to be able to update and view their customer info and see available stores
	
	private final Set<ApplicationUserPermission> permissions;
	
	/**
	 * 
	 * @param permissions a set containing the user permissions based off ApplicationUserPermission enum
	 */
	ApplicationUserRole(Set<ApplicationUserPermission> permissions){
		this.permissions = permissions;
	}
	
	/**
	 * 
	 * @return the set containing the user permissions based off ApplicationUserPermission enum
	 */
	public Set<ApplicationUserPermission> getPermissions(){
		return permissions;
	}
	
	/**
	 * 
	 * @return the user's granted Authorities based off the users permissions
	 */
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions = 
		getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}
}