package com.saurin.magiawala.impl.jpaentities;



import javax.persistence.Entity;
import com.saurin.magiawala.security.domain.AppNameSpace;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class AppNameSpaceEntity extends NameObjectImpl implements AppNameSpace 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
