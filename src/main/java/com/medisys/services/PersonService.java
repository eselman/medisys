package com.medisys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medisys.entities.Doctor;
import com.medisys.entities.IdentificationType;
import com.medisys.entities.Patient;
import com.medisys.entities.Specialty;
import com.medisys.repositories.DoctorRepository;
import com.medisys.repositories.IdentificationTypeRepository;
import com.medisys.repositories.PatientRepository;
import com.medisys.repositories.SpecialtyRepository;

@Service
public class PersonService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private IdentificationTypeRepository identificationTypeRepository;
	
	@Autowired
	private SpecialtyRepository specialtyRepository;

	// =============================================== DOCTOR ==================================================================
	/**
	 * Returns a doctor by id.
	 * 
	 * @param idType
	 * @param idNumber
	 * @return
	 */
	public Doctor getDoctorById(String idType, String idNumber) {
		return doctorRepository.findDoctorById(idType.toUpperCase(), idNumber.toUpperCase());
	}

	/**
	 * Returns a list of doctors by last name.
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Doctor> getDoctorsByLastName(String lastName) {
		return doctorRepository.findDoctorsByLastName(lastName.toUpperCase());
	}

	public Doctor saveDoctor(Doctor doctor) {
		doctor = doctorRepository.save(doctor);

		return doctor;
	}

	// =============================================== PATIENT
	// ==================================================================
	/**
	 * Returns a patient by id.
	 * 
	 * @param idType
	 * @param idNumber
	 * @return
	 */
	public Patient getPatientById(String idType, String idNumber) {
		return patientRepository.findPatientById(idType.toUpperCase(), idNumber.toUpperCase());
	}
	
	/**
	 * Returns a list of patients.
	 * @return
	 */
	public List<Patient> getPatients() {
		return (List<Patient>)patientRepository.findAll();
	}

	/**
	 * Returns a list of patients by last name.
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Patient> getPatientsByLastName(String lastName) {
		return patientRepository.findPatientsByLastName(lastName.toUpperCase());
	}

	/**
	 * Saves a patient.
	 * 
	 * @param patient
	 * @return
	 */
	public Patient savePatient(Patient patient) {
		patient = patientRepository.save(patient);

		return patient;
	}

	/**
	 * Get Identification types.
	 * 
	 * @return
	 */
	public List<IdentificationType> getIdentificationTypes() {
		Sort sortByCode = new Sort(Sort.Direction.ASC, "code");
		List<IdentificationType> identificationTypes = (List<IdentificationType>) identificationTypeRepository
				.findAll(sortByCode);
		return identificationTypes;
	}
	
	/**
	 * Get Specialties.
	 * 
	 * @return
	 */
	public List<Specialty> getSpecialties() {
		Sort sortByName = new Sort(Sort.Direction.ASC, "name");
		List<Specialty> specialties = (List<Specialty>) specialtyRepository
				.findAll(sortByName);
		return specialties;
	}
}