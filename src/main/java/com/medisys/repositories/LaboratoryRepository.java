package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.medisys.entities.Laboratory;

public interface LaboratoryRepository extends PagingAndSortingRepository<Laboratory, Long> {

}
