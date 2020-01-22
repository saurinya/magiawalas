package com.saurin.magiawala.security.assignment;

import java.util.Set;
import com.saurin.magiawala.security.NamedObject;
import com.saurin.magiawala.security.Permission;
import com.saurin.magiawala.security.domain.AppNameSpace;

/**
 * 
 * @author A Role, which can have a Group of Permissions and/Or Scoped Permission..
 * If you have a Permission without Scope and later on a Scoped Permission, th Scoped Permission Takes Precedence.
 *
 */
public interface SecurityRole extends NamedObject 
{
	/**
	 * The NameSpace to which the Role Belongs..
	 * @return
	 */
	AppNameSpace getNameSpace ();
	
	Set<Permission> getPermissions ();
	
}
