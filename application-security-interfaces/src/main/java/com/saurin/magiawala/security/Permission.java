package com.saurin.magiawala.security;

import java.util.Set;

import com.saurin.magiawala.security.domain.AppNameSpace;
import com.saurin.magiawala.security.resources.Action;
import com.saurin.magiawala.security.resources.Resource;
import com.saurin.magiawala.security.resources.Scope;

public interface Permission 
{
	/**
	 * The Action on which this permission is applicable
	 * @return
	 */
	Action getAction();
	
	
	/**
	 * The Resource on which this permission is applicable.
	 * @return
	 */
	Resource getResource ();
	
	/**
	 * The set of scopes over which this permission is applicable. 
	 * Null or Zero scopes if the permission is purely functional.
	 * @return
	 */
	Set<Scope> getScopes ();
	
	
	/**
	 * The NameSpace to which this Permission Belongs.
	 * @return
	 */
	AppNameSpace getNameSpace ();
	
	
	/**
	 * if this is a Negative Permission, then it should explicitly dissallow. (takes precedence over allowing).
	 * @return
	 */
	boolean isNegative ();
}
