package com.saurin.magiawala.security.resources;

import com.saurin.magiawala.security.NamedObject;
import com.saurin.magiawala.security.domain.AppNameSpace;

/**
 * Represents an Action that can be taken.
 * @author Saurin Magiawala
 *
 */
public interface Action extends NamedObject 
{
	public static String ANY = "ANY";		// any Action.
	
	/**
	 * The namespace to which the Resource Belongs.
	 */
	public AppNameSpace getNameSpace ();
}
