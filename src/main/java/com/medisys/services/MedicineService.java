package com.medisys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.medisys.entities.Laboratory;
import com.medisys.entities.Medicine;
import com.medisys.entities.Presentation;
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
	 * Save/Update a laboratory.
	 * 
	 * @param laboratory
	 * @return
	 */
	public Laboratory saveLaboratory (Laboratory laboratory){
		laboratory = laboratoryRepository.save(laboratory);
		
		return laboratory;
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
	
	/**
	 * Save/Update a medicine.
	 * 
	 * @param medicine
	 * @return
	 */
	public Medicine saveMedicine (Medicine medicine){
		medicine = medicineRepository.save(medicine);
		
		return medicine;
	}
	
	/**
	 * Get Presentations by medicine id.
	 * 
	 * @param medicineId
	 * @return
	 */
	public List<Presentation> getPresentations(Long medicineId) {
		List<Presentation> presentations = new ArrayList<>();
		Medicine medicine = medicineRepository.findOne(medicineId);
		
		if (medicine.getPresentations() != null && medicine.getPresentations().size() > 0) {
			presentations.addAll(medicine.getPresentations());
		}
		
		return presentations;
	}
}