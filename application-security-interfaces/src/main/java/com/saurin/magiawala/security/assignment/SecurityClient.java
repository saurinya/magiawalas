package com.saurin.magiawala.security.assignment;

import java.util.Set;

import com.saurin.magiawala.security.NamedObject;


public interface SecurityClient extends NamedObject 
{
	
	Set<SecurityGroup> getMembershipGroups();
}
