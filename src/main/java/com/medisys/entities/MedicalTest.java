package com.medisys.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medical_test")
public class MedicalTest {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@Column(name = "result")
	private String result;
	
	@ManyToOne
	@JoinColumn(name = "test_type_id", nullable = false)
	private MedicalTestType testType;
	
	@ManyToOne
	@JoinColumn(name = "patient_history_detail_id")
	private PatientHistoryDetail patientHistoryDetail;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public MedicalTestType getTestType() {
		return testType;
	}

	public void setTestType(MedicalTestType testType) {
		this.testType = testType;
	}

	public PatientHistoryDetail getPatientHistoryDetail() {
		return patientHistoryDetail;
	}

	public void setPatientHistoryDetail(PatientHistoryDetail patientHistoryDetail) {
		this.patientHistoryDetail = patientHistoryDetail;
	}
}
