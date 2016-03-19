package com.medisys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medisys.entities.MedicalInsurance;
import com.medisys.entities.MedicalTestType;
import com.medisys.repositories.MedicalInsuranceRepository;
import com.medisys.repositories.MedicalTestTypeRepository;

@Service
public class MedicalParamsService {

	@Autowired
	private MedicalInsuranceRepository medicalInsuranceRepository;

	@Autowired
	private MedicalTestTypeRepository medicalTestTypeRepository;

	/**
	 * Return the list of medical insurances.
	 * 
	 * @return
	 */
	public List<MedicalInsurance> getMedicalInsurances() {
		Sort sortBySymbol = new Sort(Sort.Direction.ASC, "symbol");
		return (List<MedicalInsurance>) medicalInsuranceRepository.findAll(sortBySymbol);
	}

	/**
	 * Return the list of medical test types.
	 * 
	 * @return
	 */
	public List<MedicalTestType> getMedicalTestTypes() {
		Sort sortBySymbol = new Sort(Sort.Direction.ASC, "symbol");
		return (List<MedicalTestType>) medicalTestTypeRepository.findAll(sortBySymbol);
	}
}