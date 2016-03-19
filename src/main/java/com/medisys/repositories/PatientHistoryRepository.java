package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.PatientHistory;

public interface PatientHistoryRepository extends PagingAndSortingRepository<PatientHistory, Long> {
	PatientHistory findPatientHistoryByPatientId(@Param("idType") String idType, @Param("idNumber") String idNumber);
	
	PatientHistory findPatientHistoryByLastName(@Param("lastName") String lastName);
}
