package com.medisys.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
@NamedQueries ({
		@NamedQuery(name="Doctor.findDoctorById", query="SELECT d FROM Doctor d WHERE d.identificationType.code = :idType"
			+ " AND d.identificationNumber = :idNumber"),
		@NamedQuery(name="Doctor.findDoctorsByLastName", query="SELECT d FROM Doctor d WHERE d.lastName = :lastName")
})
public class Doctor extends Person {
	@ManyToOne
	@JoinColumn(name = "specialty_id")
	private Specialty specialty;

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}
