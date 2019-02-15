package com.zagwork.creditlimit.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zagwork.creditlimit.converters.RiskConverter;
import com.zagwork.creditlimit.enums.Risk;

@Entity
@Table(name="TCL_CREDIT_LIMIT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditLimit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6083942573782147154L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CRE_LIM_ID", nullable = false)
	private Long id;
	
	@Column(name = "CRE_LIM_CUS_NM", nullable = false)
	private String customerName;
	
	@Column(name = "CRE_LIM_AMO", nullable = false)
	private BigDecimal creditLimitAmount;
	
	@Column(name = "CRE_LIM_RIS", nullable = false)
	@Convert(converter=RiskConverter.class)
	private Risk risk;
	
	@Column(name = "CRE_LIM_FEE", nullable = false)
	private BigDecimal fees;
	
	public CreditLimit() {}
	
	public CreditLimit(Long id, String customerName, BigDecimal creditLimitAmount, Risk risk, BigDecimal fees) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.creditLimitAmount = creditLimitAmount;
		this.risk = risk;
		this.fees = fees;
	}

	public CreditLimit(String customerName, BigDecimal creditLimitAmount, Risk risk, BigDecimal fees) {
		super();
		this.customerName = customerName;
		this.creditLimitAmount = creditLimitAmount;
		this.risk = risk;
		this.fees = fees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(BigDecimal creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}
		
}
