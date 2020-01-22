package com.saurin.magiawala.impl.jpaentities;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.saurin.magiawala.security.domain.AppNameSpace;
import com.saurin.magiawala.security.resources.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceEntity extends NameObjectImpl implements Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = AppNameSpaceEntity.class)
	AppNameSpace nameSpace;
}
