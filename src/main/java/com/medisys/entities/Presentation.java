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
@Table(name = "presentation")
public class Presentation {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "quantity", nullable = false)
	private Long quantity;

	@Column(name = "dose", nullable = false)
	private Double dose;

	@ManyToOne
	@JoinColumn(name = "measure_unit_id", nullable = false)
	private MeasureUnit measureUnit;

	@ManyToOne
	@JoinColumn(name = "presentation_type_id", nullable = false)
	private PresentationType presentationType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getDose() {
		return dose;
	}

	public void setDose(Double dose) {
		this.dose = dose;
	}

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public PresentationType getPresentationType() {
		return presentationType;
	}

	public void setPresentationType(PresentationType presentationType) {
		this.presentationType = presentationType;
	}
}
