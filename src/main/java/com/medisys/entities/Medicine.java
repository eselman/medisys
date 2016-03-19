package com.medisys.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "medicine")
@NamedQueries (
		@NamedQuery( name="Medicine.findMedicines", query="SELECT m FROM Medicine m WHERE m.brandName LIKE :query"
			+ " OR m.genericName LIKE :query ORDER BY m.genericName, m.brandName")
)
public class Medicine implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "brand_name", nullable = false)
	private String brandName;

	@Column(name = "generic_name", nullable = false)
	private String genericName;

	@ManyToOne
	@JoinColumn(name = "laboratory_id", nullable = false)
	private Laboratory laboratory;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "medicine_presentation", joinColumns = { @JoinColumn(name = "medicine_id") }, inverseJoinColumns = { @JoinColumn(name = "presentation_id") })
	private Set<Presentation> presentations = new HashSet<Presentation>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	public Set<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
	}
}
