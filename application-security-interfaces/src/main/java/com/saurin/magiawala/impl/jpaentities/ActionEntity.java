package com.saurin.magiawala.impl.jpaentities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.saurin.magiawala.security.domain.AppNameSpace;
import com.saurin.magiawala.security.resources.Action;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class ActionEntity extends NameObjectImpl implements Action 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne (targetEntity = AppNameSpaceEntity.class)
	AppNameSpace nameSpace;
}
