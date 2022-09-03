package org.dxc.OneToOne.Entity;

import javax.persistence.*;

@Entity
public class Husband {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	int id;
	String name;
	@OneToOne(targetEntity = Wife.class,cascade = CascadeType.ALL)
	Wife wife;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}


}
