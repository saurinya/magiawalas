package com.saurin.magiawala.impl.jpaentities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.saurin.magiawala.security.assignment.SecurityClient;
import com.saurin.magiawala.security.assignment.SecurityGroup;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class SecurityClientEntity extends NameObjectImpl implements SecurityClient 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@OneToMany(targetEntity = SecurityGroupEntity.class)
	Set<SecurityGroup> membershipGroups;
}
