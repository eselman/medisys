package com.medisys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.medisys.entities.Laboratory;
import com.medisys.entities.Medicine;
import com.medisys.repositories.LaboratoryRepository;
import com.medisys.repositories.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;

	/**
	 * Return the list of laboratories.
	 * 
	 * @return
	 */
	public List<Laboratory> getLaboratories() {
		Sort sortByName = new Sort(Sort.Direction.ASC, "name");
		return (List<Laboratory>) laboratoryRepository.findAll(sortByName);
	}
	
	/**
	 * Return the list of medicines by laboratory.
	 * 
	 * @param laboratoryId
	 * @return
	 */
	public List<Medicine> getMedicinesByLaboratory (Long laboratoryId) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		
		Laboratory laboratory = laboratoryRepository.findOne(laboratoryId);
		
		if (laboratory != null) {
			Sort sortByBrandName = new Sort(Sort.Direction.ASC, "brandName");
			medicines = medicineRepository.findByLaboratory(laboratory, sortByBrandName);
		}
		
		return medicines;
	}
	
	/**
	 * Search medicines where brandName or genericName contains query.
	 * 
	 * @param query
	 * @return
	 */
	public List<Medicine> searchMedicines(String query) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		
		if (!StringUtils.isEmpty(query)) {
			String queryStr = "%" + query.toUpperCase() + "%";
			medicines = medicineRepository.findMedicines(queryStr);
		} else {
			Sort sortByBrandName = new Sort(Sort.Direction.ASC, "brandName");
			medicines = (List<Medicine>) medicineRepository.findAll(sortByBrandName);
		}
		
		return medicines;
	}
	
	public Medicine saveMedicine (Medicine medicine){
		medicine = medicineRepository.save(medicine);
		
		return medicine;
	}
}