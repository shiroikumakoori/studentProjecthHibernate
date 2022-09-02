package org.Entity;


import javax.persistence.*;


@Entity
public class PatientData {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	int Id;
	String firstName;
	String lastName;
	int age;
	
	@OneToOne(targetEntity = ClinicalDataHeight.class , cascade = CascadeType.ALL)
	ClinicalDataHeight clinicObj;
	
	

	public ClinicalDataHeight getClinicObj() {
		return clinicObj;
	}
	public void setClinicObj(ClinicalDataHeight clinicObj) {
		this.clinicObj = clinicObj;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
}
