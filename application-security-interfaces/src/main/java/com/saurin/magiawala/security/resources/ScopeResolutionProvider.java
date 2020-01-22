package com.saurin.magiawala.security.resources;

public interface ScopeResolutionProvider 
{
	public boolean isResourceInScope (String resourceId,String resourceTypeValue,Scope scope);
}
