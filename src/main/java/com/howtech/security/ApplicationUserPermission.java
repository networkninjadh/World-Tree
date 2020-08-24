package com.howtech.security;

/**
 * 
 * @author Damond Howard
 * @apiNote enum containing certain application user permissions has sets
 *
 */
public enum ApplicationUserPermission {
	STORE_READ("store:read"),
	STORE_WRITE("store:write"),
	CUSTOMER_READ("customer:read"),
	CUSTOMER_WRITE("customer:write");
	//TODO: add the rest of the permissions to the list
	private final String permission;
	
	/**
	 * 
	 * @param creates a set that represents a permission
	 */
	ApplicationUserPermission(String permission){
		this.permission = permission;
	}
	
	/**
	 * 
	 * @return the permission 
	 */
	public String getPermission() {
		return permission;
	}
}
