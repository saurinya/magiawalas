package com.saurin.magiawala.impl.jpaentities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.saurin.magiawala.security.assignment.SecurityGroup;
import com.saurin.magiawala.security.assignment.SecurityRole;
import com.saurin.magiawala.security.domain.AppNameSpace;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class SecurityGroupEntity extends NameObjectImpl implements SecurityGroup 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = AppNameSpaceEntity.class)
	AppNameSpace nameSpace;
	
	@OneToMany(targetEntity = SecurityRoleEntity.class)
	Set<SecurityRole> roles;
	
	@ManyToOne(targetEntity = SecurityGroupEntity.class)
	SecurityGroup parentGroup;
}
