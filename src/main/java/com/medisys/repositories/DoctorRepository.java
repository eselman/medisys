package com.medisys.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.Doctor;

public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long> {
	Doctor findDoctorById(@Param("idType") String idType, @Param("idNumber") String idNumber);
	
	List<Doctor> findDoctorsByLastName(@Param("lastName") String lastName);
}
