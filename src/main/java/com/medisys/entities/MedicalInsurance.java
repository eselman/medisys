package com.medisys.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="medical_insurance")
public class MedicalInsurance {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (name="symbol", unique = true, nullable = false)
	private String symbol;
	
	@Column (name="description", nullable = false)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
