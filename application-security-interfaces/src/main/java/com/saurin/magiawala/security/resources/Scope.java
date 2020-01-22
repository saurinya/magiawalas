package com.saurin.magiawala.security.resources;

import com.saurin.magiawala.security.NamedObject;
import com.saurin.magiawala.security.domain.AppNameSpace;

/**
 * A Scope over which a Permission needs to be applied.
 * @author Saurin Magiawala
 *
 */
public interface Scope extends NamedObject 
{
	/**
	 * The namespace to which the Resource Belongs.
	 */
	public AppNameSpace getNameSpace ();
	
	
	/**
	 * True if this scope allows permission, false if it is opposite.
	 * @return
	 */
	public boolean isAllowing ();
	
	/**
	 * the priority of considering this scope
	 * @return
	 */
	public int getPriority ();
	
	
	/**
	 * The type of scope that this is..
	 * @return
	 */
	public int getScopeType ();

}
