package com.rabobank.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author 738575
 *
 */
public class CustomerStatementRecord {	
	
	private long transactionRefrence;
	
	@JsonIgnore
	private String acctnumber;
	
	private String description;
	
	@JsonIgnore
	private BigDecimal startingBalance;
	
	@JsonIgnore
	private BigDecimal mutation;
	
	@JsonIgnore
	private BigDecimal endBalance;
	
	/**
	 * @param transactionRefrence
	 * @param acctnumber
	 * @param description
	 * @param startingBalance
	 * @param mutation
	 * @param endBalance
	 */
	
	public CustomerStatementRecord(long transactionRefrence, String acctnumber, String description,
			BigDecimal startingBalance, BigDecimal mutation, BigDecimal endBalance) {
		super();
		this.transactionRefrence = transactionRefrence;
		this.acctnumber = acctnumber;
		this.description = description;
		this.startingBalance = startingBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}
	
	/**
	 * @return the transactionRefrence
	 */
	public long getTransactionRefrence() {
		return transactionRefrence;
	}

	/**
	 * @param transactionRefrence the transactionRefrence to set
	 */
	public void setTransactionRefrence(long transactionRefrence) {
		this.transactionRefrence = transactionRefrence;
	}

	/**
	 * @return the acctnumber
	 */
	public String getAcctnumber() {
		return acctnumber;
	}

	/**
	 * @param acctnumber the acctnumber to set
	 */
	public void setAcctnumber(String acctnumber) {
		this.acctnumber = acctnumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startingBalance
	 */
	public BigDecimal getStartingBalance() {
		return startingBalance;
	}

	/**
	 * @param startingBalance the startingBalance to set
	 */
	public void setStartingBalance(BigDecimal startingBalance) {
		this.startingBalance = startingBalance;
	}

	/**
	 * @return the mutation
	 */
	public BigDecimal getMutation() {
		return mutation;
	}

	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	/**
	 * @return the endBalance
	 */
	public BigDecimal getEndBalance() {
		return endBalance;
	}

	/**
	 * @param endBalance the endBalance to set
	 */
	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {
		return "CustomerStatementRecord [transactionRefrence=" + transactionRefrence + ", acctnumber=" + acctnumber
				+ ", description=" + description + ", startingBalance=" + startingBalance + ", mutation=" + mutation
				+ ", endBalance=" + endBalance + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(acctnumber, description, endBalance, mutation, startingBalance, transactionRefrence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CustomerStatementRecord other = (CustomerStatementRecord) obj;
		return Objects.equals(acctnumber, other.acctnumber) && Objects.equals(description, other.description)
				&& Objects.equals(endBalance, other.endBalance) && Objects.equals(mutation, other.mutation)
				&& Objects.equals(startingBalance, other.startingBalance)
				&& transactionRefrence == other.transactionRefrence;
	}
	
	
}
