package com.medisys.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.Country;
import com.medisys.entities.County;

public interface CountyRepository extends PagingAndSortingRepository<County, Long> {
	List<County> findByCountry(@Param("country") Country country, Sort sortByCode);
	
	County findByCode(@Param("countyCode") String countyCode);
}
