package com.ayantsoft.scheduler.hibernate.pojo;
// Generated 21 Sep, 2017 2:24:30 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CandidateVisa generated by hbm2java
 */
@Entity
@Table(name = "candidate_visa", catalog = "ayant_trmsv2")
public class CandidateVisa implements java.io.Serializable {

	private Integer id;
	private String description;
	private String visa;

	public CandidateVisa() {
	}

	public CandidateVisa(String description, String visa) {
		this.description = description;
		this.visa = visa;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "visa", length = 45)
	public String getVisa() {
		return this.visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

}
