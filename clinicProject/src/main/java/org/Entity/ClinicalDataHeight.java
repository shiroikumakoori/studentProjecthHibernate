package org.Entity;

import javax.persistence.*;
@Entity
public class ClinicalDataHeight {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
//	@Column(nullable = false)
	String componetName;
//	@Column(nullable = false)
	String componetValue;
//	@Column(nullable = false)
	String measuredDateTime;
	
//	@OneToOne(targetEntity = PatientData.class)
//	PatientData patientId;

//	public PatientData getPatientId() {
//		return patientId;
//	}
//
//	public void setPatientId(PatientData patientId) {
//		this.patientId = patientId;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponetName() {
		return componetName;
	}

	public void setComponetName(String componetName) {
		this.componetName = componetName;
	}

	public String getComponetValue() {
		return componetValue;
	}

	public void setComponetValue(String componetValue) {
		this.componetValue = componetValue;
	}

	public String getMeasuredDateTime() {
		return measuredDateTime;
	}

	public void setMeasuredDateTime(String measuredDateTime) {
		this.measuredDateTime = measuredDateTime;
	}




	
}
