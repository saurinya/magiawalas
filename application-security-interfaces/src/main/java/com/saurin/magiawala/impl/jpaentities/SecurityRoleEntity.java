package com.saurin.magiawala.impl.jpaentities;



import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.saurin.magiawala.security.Permission;
import com.saurin.magiawala.security.assignment.SecurityRole;
import com.saurin.magiawala.security.domain.AppNameSpace;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class SecurityRoleEntity extends NameObjectImpl implements SecurityRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = AppNameSpaceEntity.class)
	AppNameSpace nameSpace;
	
	@OneToMany (targetEntity = PermissionEntity.class)
	Set<Permission> permissions;
}
