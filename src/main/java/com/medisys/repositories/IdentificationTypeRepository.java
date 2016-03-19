package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.medisys.entities.IdentificationType;

public interface IdentificationTypeRepository extends PagingAndSortingRepository<IdentificationType, Long> {

}
