package com.medisys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medisys.entities.PatientHistory;
import com.medisys.repositories.PatientHistoryRepository;

@Service
public class PatientHistoryService {

	@Autowired
	private PatientHistoryRepository patientHistoryRepository;

	/**
	 * Returns a patient history by id.
	 * 
	 * @param idType
	 * @param idNumber
	 * @return
	 */
	public PatientHistory getPatientHistoryByPatientId(String idType, String idNumber) {
		return patientHistoryRepository.findPatientHistoryByPatientId(idType.toUpperCase(), idNumber.toUpperCase());
	}

	/**
	 * Returns a patient history by last name.
	 * 
	 * @param lastName
	 * @return
	 */
	public PatientHistory getPatientHistoryByLastName(String lastName) {
		return patientHistoryRepository.findPatientHistoryByLastName(lastName.toUpperCase());
	}

	public PatientHistory savePatientHistory(PatientHistory patientHistory) {
		patientHistory = patientHistoryRepository.save(patientHistory);

		return patientHistory;
	}
}