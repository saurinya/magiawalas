package com.saurin.magiawala.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.saurin.magiawala.security.Permission;
import com.saurin.magiawala.security.SecurityManager;
import com.saurin.magiawala.security.assignment.SecurityClient;
import com.saurin.magiawala.security.assignment.SecurityGroup;
import com.saurin.magiawala.security.assignment.SecurityRole;
import com.saurin.magiawala.security.resources.Action;
import com.saurin.magiawala.security.resources.Resource;
import com.saurin.magiawala.security.resources.Scope;
import com.saurin.magiawala.security.resources.ScopeResolutionProvider;

import lombok.Data;

@Data
public class SecurityManagerImpl implements SecurityManager 
{
	SecurityClient client;
	ScopeResolutionProvider scopeResolutionProvider;
	
	/**
	 * set of permissions on "action.resource"  The internal hashmap is the name of the Role which provides the permission.
	 */
	HashMap<String, Set<Permission>> positivePermissions = new HashMap<String, Set<Permission>>();
	
	/**
	 * These are explicit denials, which take precendence over allowance
	 */
	HashMap<String, Set<Permission>> negativePermissions = new HashMap<String, Set<Permission>>();
	
	
	public SecurityManagerImpl (SecurityClient client,ScopeResolutionProvider scopeResolutionProvider)
	{
		this.client = client;
		this.scopeResolutionProvider = scopeResolutionProvider;
		
		if (client == null)
			throw new RuntimeException("SecurityClient must be provided in the constructor and should not be null.");
		
		Set<SecurityRole> additonalRoles = null;
		setUpPermissioMaps(client,additonalRoles);
	}
	
	
	public SecurityManagerImpl (SecurityClient client,ScopeResolutionProvider scopeResolutionProvider,Set<SecurityRole> additionalRoles)
	{
		this.client = client;
		this.scopeResolutionProvider = scopeResolutionProvider;
		
		if (client == null)
			throw new RuntimeException("SecurityClient must be provided in the constructor and should not be null.");
		
		setUpPermissioMaps(client,additionalRoles);
	}
	

	private String getPermissionKey (Action action,Resource resource)
	{
		return "" + action.getValue() + "." + resource.getValue();
	}
	
	private String getPermissionKey (Serializable actionTypeValue,Serializable resourceTypeValue)
	{
		return "" + actionTypeValue + "." + resourceTypeValue;
	}
	
	
	private void setUpPermissionMapForRoles (Set<SecurityRole> securityRoles)
	{
		if (securityRoles != null)
		{
			for (SecurityRole sr: securityRoles)
			{
				Set<Permission> permissions = sr.getPermissions();
				
				if (permissions != null)
				{
					for (Permission p: permissions)
					{
						Resource resource = p.getResource();
						Action action = p.getAction();
						
						String permissionKey = getPermissionKey(action, resource);
						Set<Permission> permissionSet;
						
						if (p.isNegative())
						{
							permissionSet = negativePermissions.get(permissionKey);
							
							if (permissionSet == null)
							{
								permissionSet=new HashSet<Permission>();
								negativePermissions.put(permissionKey,permissionSet);
							}
						}
						else
						{
							permissionSet = positivePermissions.get(permissionKey);
							if (permissionSet == null)
							{
								permissionSet=new HashSet<Permission>();
								negativePermissions.put(permissionKey,permissionSet);
							}
						}
						
						permissionSet.add(p);
					}
				}
			}
		}		
	}
	
	private void setUpPermissioMaps(SecurityClient sclient,Set<SecurityRole> additionalRoles)
	{
		Set<SecurityGroup> membershipGroups = sclient.getMembershipGroups();
		
		if (membershipGroups != null)
		{
			for (SecurityGroup sg: membershipGroups)
			{
				Set<SecurityRole> securityRoles = sg.getRoles();
				setUpPermissionMapForRoles(securityRoles);
			}
		}
		
		setUpPermissionMapForRoles(additionalRoles);
	}
	
	public boolean isAllowed(String resourceId, String actionTypeValue, String resourceTypeValue) 
	{
		Set<Permission> allowingPermissions = getAllowingPermissions(actionTypeValue, resourceTypeValue);
		if (allowingPermissions == null || allowingPermissions.isEmpty())
			return false;
		
		Set<Scope> allowingScopes = null;
		
		// Okay since functionally it is allowed, lets go through the permissions and figure out if the scopes allow it.
		for (Permission p: allowingPermissions)
		{
			if (p != null && p.getScopes() != null && p.getScopes().size() > 0)
			{
				if (allowingScopes == null)
					allowingScopes = new HashSet<Scope>();
				
				allowingScopes.addAll(p.getScopes());
			}
		}
		
		if (allowingScopes == null || allowingScopes.isEmpty())
			return true;	// Okay no scope is restricting access..
		
		if (scopeResolutionProvider == null)
			throw new RuntimeException("A scope resolution provider has not been configured.");
		
		List<Scope> listOfScopes = new ArrayList<Scope>();
		listOfScopes.addAll(allowingScopes);
		
		// Lets sort it in the order of priority.
		Collections.sort(listOfScopes, (o1, o2) -> { return (o1.getPriority() - o2.getPriority());});

		// Okay so now that it is sorted.
		for (Scope scope: listOfScopes)
		{
			boolean b = scopeResolutionProvider.isResourceInScope(resourceId, resourceTypeValue, scope);
			
			if (b) // Okay so resource is in this scope..
			{
				if (scope.isAllowing())
					return true;
				else
					return false;
			}
		}
		
		return false;
	}

	/**
	 * Returns a Set of Permissions which allows this... null if it is NOT allowed.
	 */
	public Set<Permission> getAllowingPermissions (Serializable actionTypeValue, Serializable resourceTypeValue) 
	{
		String permissionKey = getPermissionKey(actionTypeValue, resourceTypeValue);
		
		String anyActionOnAnyResourceKey = getPermissionKey(Action.ANY, Resource.ANY);
		
		if (negativePermissions.containsKey(anyActionOnAnyResourceKey))		// Explicit denial of any action on any resource..
			return null;
		
		String thisActionOnAnyResourceKey = getPermissionKey(actionTypeValue, Resource.ANY);
		if (negativePermissions.containsKey(thisActionOnAnyResourceKey))
			return null;
		
		String thisResourceOnAnyActionKey = getPermissionKey(Action.ANY, resourceTypeValue);
		if (negativePermissions.containsKey(thisResourceOnAnyActionKey))
			return null;
		
		if (negativePermissions.containsKey(permissionKey))
			return null;

		Set<Permission> allowingPermissions = new HashSet<Permission>();
		
		if (positivePermissions.containsKey(anyActionOnAnyResourceKey))
			allowingPermissions.addAll(positivePermissions.get(anyActionOnAnyResourceKey));
		
		if (positivePermissions.containsKey(thisResourceOnAnyActionKey))
			allowingPermissions.addAll(positivePermissions.get(thisResourceOnAnyActionKey));
		
		if (positivePermissions.containsKey(thisActionOnAnyResourceKey))
			allowingPermissions.addAll(positivePermissions.get(thisActionOnAnyResourceKey));
		
		if (positivePermissions.containsKey(permissionKey))
			allowingPermissions.addAll(positivePermissions.get(permissionKey));
			
		if (allowingPermissions.size() > 0)
			return allowingPermissions;
		
		return null;
	}


	/**
	 * REturns true of false on whether it is allowed or not..
	 */
	public boolean isAllowed(String actionTypeValue, String resourceTypeValue) 
	{
		Set<Permission> allowingPermissions = getAllowingPermissions(actionTypeValue, resourceTypeValue);
		
		if (allowingPermissions != null && allowingPermissions.size() > 0)
			return true;
		
		return false;
	}

}
