package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.medisys.entities.MedicalTestType;

public interface MedicalTestTypeRepository extends PagingAndSortingRepository<MedicalTestType, Long> {

}
