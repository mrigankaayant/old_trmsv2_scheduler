package com.ayantsoft.scheduler.hibernate.pojo;
// Generated 21 Sep, 2017 2:24:30 PM by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IncentiveStructure generated by hbm2java
 */
@Entity
@Table(name = "incentive_structure", catalog = "ayant_trmsv2")
public class IncentiveStructure implements java.io.Serializable {

	private Integer id;
	private BigDecimal targetLowerLimit;
	private BigDecimal targetHigherLimit;
	private BigDecimal incentiveInInr;
	private String typeFor;

	public IncentiveStructure() {
	}

	public IncentiveStructure(BigDecimal targetLowerLimit, BigDecimal targetHigherLimit, BigDecimal incentiveInInr) {
		this.targetLowerLimit = targetLowerLimit;
		this.targetHigherLimit = targetHigherLimit;
		this.incentiveInInr = incentiveInInr;
	}

	public IncentiveStructure(BigDecimal targetLowerLimit, BigDecimal targetHigherLimit, BigDecimal incentiveInInr,
			String typeFor) {
		this.targetLowerLimit = targetLowerLimit;
		this.targetHigherLimit = targetHigherLimit;
		this.incentiveInInr = incentiveInInr;
		this.typeFor = typeFor;
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

	@Column(name = "target_lower_limit", nullable = false, precision = 8)
	public BigDecimal getTargetLowerLimit() {
		return this.targetLowerLimit;
	}

	public void setTargetLowerLimit(BigDecimal targetLowerLimit) {
		this.targetLowerLimit = targetLowerLimit;
	}

	@Column(name = "target_higher_limit", nullable = false, precision = 8)
	public BigDecimal getTargetHigherLimit() {
		return this.targetHigherLimit;
	}

	public void setTargetHigherLimit(BigDecimal targetHigherLimit) {
		this.targetHigherLimit = targetHigherLimit;
	}

	@Column(name = "incentive_in_inr", nullable = false, precision = 8)
	public BigDecimal getIncentiveInInr() {
		return this.incentiveInInr;
	}

	public void setIncentiveInInr(BigDecimal incentiveInInr) {
		this.incentiveInInr = incentiveInInr;
	}

	@Column(name = "type_for", length = 45)
	public String getTypeFor() {
		return this.typeFor;
	}

	public void setTypeFor(String typeFor) {
		this.typeFor = typeFor;
	}

}
