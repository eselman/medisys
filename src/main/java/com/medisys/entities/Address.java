package com.medisys.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="address")
public class Address {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (name="street")
	private String street;
	
	@Column (name="number")
	private String number;
	
	@Column (name="floor")
	private String floor;
	
	@Column (name="apartment")
	private String apartment;
	
	@ManyToOne
	@JoinColumn(name = "town_id", nullable = false)
	private Town town;
	
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "county_id", nullable = false)
	private County county;
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
} 
