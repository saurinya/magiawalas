package com.saurin.magiawala.security;

import java.io.Serializable;

/**
 * A named object (has a Unique ID, name and description.
 * @author Saurin Magiawala
 *
 */
public interface NamedObject extends java.io.Serializable
{

	/**
	 * Unique ID globally..
	 * @return
	 */
	public String getId ();
	
	
	/**
	 * A Name for the domain
	 * @return
	 */
	public String getName ();
	
	
	
	/**
	 * Description
	 * @return
	 */
	public String getDescription ();
	
	
	
	/**
	 * The value of this Object.
	 * this could be null, or throw an exception if it does not make sense to have a value.
	 */
	public Serializable getValue ();

}
