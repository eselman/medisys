package com.medisys.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.Laboratory;
import com.medisys.entities.Medicine;

public interface MedicineRepository extends PagingAndSortingRepository<Medicine, Long> {
	List<Medicine> findByLaboratory(@Param("laboratory") Laboratory laboratory, Sort sortByBrandName);
	
	List<Medicine> findMedicines (@Param("query") String query);
}
