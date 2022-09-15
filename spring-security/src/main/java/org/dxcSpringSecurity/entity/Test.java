package org.dxcSpringSecurity.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table
public class Test {
	@Id
	private long id;
	private String name;
}
