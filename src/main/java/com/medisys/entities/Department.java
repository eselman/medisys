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
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "name")
	private String name;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
