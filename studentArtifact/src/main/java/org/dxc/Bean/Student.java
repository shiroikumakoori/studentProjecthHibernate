package org.dxc.Bean;

import jakarta.persistence.*;


@Entity
@Table(name="STUDENT")
public class Student {
	@Id
	private int sid;
	@Column(name="sname")
	private String sname;
	@Column(name="saddr")
	private String saddr;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddr() {
		return saddr;
	}
	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}

}
