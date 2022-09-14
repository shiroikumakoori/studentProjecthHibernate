package com.microservice.department.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepratmentAddress() {
		return depratmentAddress;
	}
	public void setDepratmentAddress(String depratmentAddress) {
		this.depratmentAddress = depratmentAddress;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	private String departmentName;
	private String depratmentAddress;
	private String departmentCode;
}
