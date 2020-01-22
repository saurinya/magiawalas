package com.saurin.magiawala.impl.jpaentities;



import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.saurin.magiawala.security.Permission;
import com.saurin.magiawala.security.domain.AppNameSpace;
import com.saurin.magiawala.security.resources.Action;
import com.saurin.magiawala.security.resources.Resource;
import com.saurin.magiawala.security.resources.Scope;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class PermissionEntity extends NameObjectImpl implements Permission 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic
	boolean negative;
	
	@ManyToOne(targetEntity = AppNameSpaceEntity.class)
	AppNameSpace nameSpace;
	
	@ManyToOne(targetEntity = ActionEntity.class)
	Action action;
	
	@ManyToOne(targetEntity = ResourceEntity.class)
	Resource resource;
	
	@OneToMany(targetEntity = ScopeEntity.class)
	Set<Scope> scopes;
	
}
