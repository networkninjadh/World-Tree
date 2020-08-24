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
	USER(Sets.newHashSet()); //needs to be able to fill orders access inventory
	
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