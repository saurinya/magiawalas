package com.saurin.magiawala.security;


public interface SecurityManager 
{	
	/**
	 * returns true if allowed action on the resource (objectId is the unique id for that object).
	 * @param resourceId  - NULLABLE The id of the object under consideration on which the permission allowed/disallowed decision is required..
	 *                          - could be null if you asking generally and not for a specific object.
	 * @param actionTypeValue   - the action to be performed.
	 * @param resourTypeceValue - the resource type on which the action has to be performed.
	 * @return
	 */
	boolean isAllowed (String resourceId,String actionTypeValue,String resourceTypeValue);
	
	
	/**
	 * same as isAllowed but without the resourceId.. so permission at a functional level, not data level.
	 * @param clientId - the client for which it is desired.
	 * @param actionTypeValue - the action desired
	 * @param resourceTypeValue - the resourcetype on which the action is required.
	 * @return
	 */
	boolean isAllowed (String actionTypeValue,String resourceTypeValue);
	
}
