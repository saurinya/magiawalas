package com.saurin.magiawala.security.assignment;

import java.util.Set;
import com.saurin.magiawala.security.NamedObject;
import com.saurin.magiawala.security.domain.AppNameSpace;

/**
 * A Security Group representing a Group of Users..
 * @author Saurin Magiawala
 *
 */
public interface SecurityGroup extends NamedObject 
{
	/**
	 * The Security NameSpace to which the Group Belongs..
	 * @return
	 */
	AppNameSpace getNameSpace ();
	
	/**
	 * The Roles that the Security Group Has.
	 * @return
	 */
	Set<SecurityRole> getRoles ();
	
	
	/**
	 * The Parent Group to which this Security Group Belongs..
	 * @return
	 */
	SecurityGroup getParentGroup ();
	
}
