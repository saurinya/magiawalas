package com.saurin.magiawala.impl.jpaentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

import com.saurin.magiawala.security.NamedObject;

import lombok.Data;

@Data
public class NameObjectImpl implements NamedObject 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic
	String id;
	
	@Column
	@Basic
	String name;
	
	@Column
	@Basic
	String description;
	
	@Column
	@Basic
	Serializable value;
}
