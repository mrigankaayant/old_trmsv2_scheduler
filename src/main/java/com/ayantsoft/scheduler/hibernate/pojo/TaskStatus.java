package com.ayantsoft.scheduler.hibernate.pojo;
// Generated 21 Sep, 2017 2:24:30 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TaskStatus generated by hbm2java
 */
@Entity
@Table(name = "task_status", catalog = "ayant_trmsv2")
public class TaskStatus implements java.io.Serializable {

	private Integer statusId;
	private String description;
	private String statusName;

	public TaskStatus() {
	}

	public TaskStatus(String description, String statusName) {
		this.description = description;
		this.statusName = statusName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status_name", length = 100)
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
