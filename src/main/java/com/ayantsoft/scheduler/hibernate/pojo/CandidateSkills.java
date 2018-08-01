package com.ayantsoft.scheduler.hibernate.pojo;
// Generated 21 Sep, 2017 2:24:30 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CandidateSkills generated by hbm2java
 */
@Entity
@Table(name = "candidate_skills", catalog = "ayant_trmsv2")
public class CandidateSkills implements java.io.Serializable {

	private Integer id;
	private Candidate candidate;
	private String expertiesLevel;
	private Date lastUsed;
	private String techSkill;
	private Integer yearsOfExp;

	public CandidateSkills() {
	}

	public CandidateSkills(Candidate candidate) {
		this.candidate = candidate;
	}

	public CandidateSkills(Candidate candidate, String expertiesLevel, Date lastUsed, String techSkill,
			Integer yearsOfExp) {
		this.candidate = candidate;
		this.expertiesLevel = expertiesLevel;
		this.lastUsed = lastUsed;
		this.techSkill = techSkill;
		this.yearsOfExp = yearsOfExp;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id", nullable = false)
	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Column(name = "experties_level", length = 45)
	public String getExpertiesLevel() {
		return this.expertiesLevel;
	}

	public void setExpertiesLevel(String expertiesLevel) {
		this.expertiesLevel = expertiesLevel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_used", length = 10)
	public Date getLastUsed() {
		return this.lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	@Column(name = "tech_skill", length = 45)
	public String getTechSkill() {
		return this.techSkill;
	}

	public void setTechSkill(String techSkill) {
		this.techSkill = techSkill;
	}

	@Column(name = "years_of_exp")
	public Integer getYearsOfExp() {
		return this.yearsOfExp;
	}

	public void setYearsOfExp(Integer yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

}
