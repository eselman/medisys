package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.medisys.entities.Specialty;

public interface SpecialtyRepository extends PagingAndSortingRepository<Specialty, Long> {

}
