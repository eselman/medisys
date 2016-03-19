package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.medisys.entities.MedicalInsurance;

public interface MedicalInsuranceRepository extends PagingAndSortingRepository<MedicalInsurance, Long> {

}
