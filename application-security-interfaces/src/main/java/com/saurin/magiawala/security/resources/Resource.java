package com.saurin.magiawala.security.resources;

import com.saurin.magiawala.security.NamedObject;
import com.saurin.magiawala.security.domain.AppNameSpace;

/**
 * Represents a resource that needs to be protected.
 * @author Saurin Magiawala
 *
 */
public interface Resource extends NamedObject 
{
	public static String ANY = "ANY";		// any Resource..
	
	/**
	 * The namespace to which the Resource Belongs.
	 */
	public AppNameSpace getNameSpace ();
}
