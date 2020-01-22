package com.saurin.magiawala.impl.jpaentities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.saurin.magiawala.security.domain.AppNameSpace;
import com.saurin.magiawala.security.resources.Scope;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class ScopeEntity extends NameObjectImpl implements Scope 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = AppNameSpaceEntity.class)
	AppNameSpace nameSpace;
	
	@Basic
	boolean allowing;
	
	@Basic
	int priority;
	
	@Basic
	int scopeType;
}
