package com.medisys.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.Department;
import com.medisys.entities.Town;

public interface TownRepository extends PagingAndSortingRepository<Town, Long> {
	List<Town> findByDepartment(@Param("department") Department department, Sort sortByCode);	
}