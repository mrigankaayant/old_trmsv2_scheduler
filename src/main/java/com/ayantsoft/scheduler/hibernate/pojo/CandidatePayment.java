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
 * CandidatePayment generated by hbm2java
 */
@Entity
@Table(name = "candidate_payment", catalog = "ayant_trmsv2")
public class CandidatePayment implements java.io.Serializable {

	private Integer paymentId;
	private Candidate candidate;
	private CandidateCourse candidateCourse;
	private Employee employee;
	private Long creadit;
	private Long debit;
	private String modeOfPayment;
	private String receiver;
	private Date transactionDate;
	private String transactionId;
	private String txnType;

	public CandidatePayment() {
	}

	public CandidatePayment(Candidate candidate, CandidateCourse candidateCourse, Employee employee) {
		this.candidate = candidate;
		this.candidateCourse = candidateCourse;
		this.employee = employee;
	}

	public CandidatePayment(Candidate candidate, CandidateCourse candidateCourse, Employee employee, Long creadit,
			Long debit, String modeOfPayment, String receiver, Date transactionDate, String transactionId,
			String txnType) {
		this.candidate = candidate;
		this.candidateCourse = candidateCourse;
		this.employee = employee;
		this.creadit = creadit;
		this.debit = debit;
		this.modeOfPayment = modeOfPayment;
		this.receiver = receiver;
		this.transactionDate = transactionDate;
		this.transactionId = transactionId;
		this.txnType = txnType;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "payment_id", unique = true, nullable = false)
	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id", nullable = false)
	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	public CandidateCourse getCandidateCourse() {
		return this.candidateCourse;
	}

	public void setCandidateCourse(CandidateCourse candidateCourse) {
		this.candidateCourse = candidateCourse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id", nullable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "creadit", precision = 10, scale = 0)
	public Long getCreadit() {
		return this.creadit;
	}

	public void setCreadit(Long creadit) {
		this.creadit = creadit;
	}

	@Column(name = "debit", precision = 10, scale = 0)
	public Long getDebit() {
		return this.debit;
	}

	public void setDebit(Long debit) {
		this.debit = debit;
	}

	@Column(name = "mode_of_payment", length = 45)
	public String getModeOfPayment() {
		return this.modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	@Column(name = "receiver", length = 60)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "transaction_date", length = 10)
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name = "transaction_id", length = 60)
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "txn_type", length = 45)
	public String getTxnType() {
		return this.txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

}
