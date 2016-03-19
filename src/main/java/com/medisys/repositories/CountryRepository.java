package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
	Country findByCode(@Param("code") String code);
}
