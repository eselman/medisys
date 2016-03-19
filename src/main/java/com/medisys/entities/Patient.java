package com.medisys.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
@NamedQueries ({
		@NamedQuery( name="Patient.findPatientById", query="SELECT p FROM Patient p WHERE p.identificationType.code = :idType"
			+ " AND p.identificationNumber = :idNumber"),
		@NamedQuery(name="Patient.findPatientsByLastName", query="SELECT p FROM Patient p WHERE p.lastName = :lastName")
})
public class Patient extends Person {
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "patient_insurance", joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = { @JoinColumn(name = "insurance_id") })
	private Set<MedicalInsurance> insurances = new HashSet<MedicalInsurance>();

	public Set<MedicalInsurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(Set<MedicalInsurance> insurances) {
		this.insurances = insurances;
	}
}
