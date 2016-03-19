package com.medisys.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "patient_history")
@NamedQueries ({
	@NamedQuery(name="PatientHistory.findPatientHistoryByPatientId", query="SELECT ph FROM PatientHistory ph WHERE ph.patient.identificationType.code = :idType"
		+ " AND ph.patient.identificationNumber = :idNumber"),
	@NamedQuery(name="PatientHistory.findPatientHistoryByLastName", query="SELECT ph FROM PatientHistory ph WHERE ph.patient.lastName = :lastName")
})
public class PatientHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
