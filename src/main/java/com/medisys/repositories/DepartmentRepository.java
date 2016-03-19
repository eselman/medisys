package com.medisys.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.County;
import com.medisys.entities.Department;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
	List<Department> findByCounty(@Param("county") County county, Sort sortByCode);
	
	Department findByCode(@Param("departmentCode") String departmentCode);
}
