package com.medisys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medisys.entities.Country;
import com.medisys.entities.County;
import com.medisys.entities.Department;
import com.medisys.entities.Town;
import com.medisys.repositories.CountryRepository;
import com.medisys.repositories.CountyRepository;
import com.medisys.repositories.DepartmentRepository;
import com.medisys.repositories.TownRepository;

@Service
public class AddressService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CountyRepository countyRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private TownRepository townRepository;

	/**
	 * Return the list of countries.
	 * 
	 * @return
	 */
	public List<Country> getCountries() {
		Sort sortByCode = new Sort(Sort.Direction.ASC, "code");
		return (List<Country>) countryRepository.findAll(sortByCode);
	}

	/**
	 * Return the list of counties filtered by countryCode.
	 * 
	 * 
	 * @param countryCode
	 * @return
	 */
	public List<County> getCountiesByCountryCode(String countryCode) {
		Sort sortByCode = new Sort(Sort.Direction.ASC, "code");

		Country country = countryRepository.findByCode(countryCode);

		List<County> counties = new ArrayList<County>();

		if (country != null) {
			counties = countyRepository.findByCountry(country, sortByCode);
		}

		return counties;
	}

	/**
	 * Return the list of departments filtered by countyCode.
	 * 
	 * @param countyCode
	 * @return
	 */
	public List<Department> getDepartmentsByCountyCode(String countyCode) {
		Sort sortByCode = new Sort(Sort.Direction.ASC, "code");

		County county = countyRepository.findByCode(countyCode);

		List<Department> departments = new ArrayList<Department>();

		if (county != null) {
			departments = departmentRepository.findByCounty(county, sortByCode);
		}

		return departments;
	}

	/**
	 * Return the list of towns filtered by departmentCode.
	 * 
	 * @param departmentCode
	 * @return
	 */
	public List<Town> getTownsByDepartmentCode(String departmentCode) {
		Sort sortByCode = new Sort(Sort.Direction.ASC, "code");

		Department department = departmentRepository.findByCode(departmentCode);

		List<Town> towns = new ArrayList<Town>();

		if (department != null) {
			towns = townRepository.findByDepartment(department, sortByCode);
		}

		return towns;
	}
}