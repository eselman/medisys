package com.medisys.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.Patient;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
	Patient findPatientById(@Param("idType") String idType, @Param("idNumber") String idNumber);
	
	List<Patient> findPatientsByLastName(@Param("lastName") String lastName);
}
