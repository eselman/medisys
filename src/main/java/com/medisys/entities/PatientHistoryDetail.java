package com.medisys.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patient_history_detail")
public class PatientHistoryDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@Column(name = "weight", nullable = false)
	private Double weight;

	@Column(name = "diagnosis")
	private String diagnosis;

	@Column(name = "symptoms")
	private String symptoms;

	@Column(name = "observations")
	private String observations;

	@ManyToOne
	@JoinColumn(name = "patient_history_id", nullable = false)
	private PatientHistory patientHistory;

	@OneToMany(mappedBy = "patientHistoryDetail")
	private Set<MedicalTest> medicalTests;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "patient_history_detail_medicine", joinColumns = { @JoinColumn(name = "patient_history_detail_id") }, inverseJoinColumns = { @JoinColumn(name = "medicine_id") })
	private Set<Medicine> medicines = new HashSet<Medicine>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public PatientHistory getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(PatientHistory patientHistory) {
		this.patientHistory = patientHistory;
	}

	public Set<MedicalTest> getMedicalTests() {
		return medicalTests;
	}

	public void setMedicalTests(Set<MedicalTest> medicalTests) {
		this.medicalTests = medicalTests;
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}
}
