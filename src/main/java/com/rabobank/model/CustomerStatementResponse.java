package com.rabobank.model;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author 738575
 *
 */
@Component
public class CustomerStatementResponse {
	
	List<CustomerStatementRecord> duplicateTransactionReference;
	List<CustomerStatementRecord> invalidEndBalance;
	
	/**
	 * @return the duplicateTransactionReference
	 */
	public List<CustomerStatementRecord> getDuplicateTransactionReference() {
		return duplicateTransactionReference;
	}
	/**
	 * @param duplicateTransactionReference the duplicateTransactionReference to set
	 */
	public void setDuplicateTransactionReference(List<CustomerStatementRecord> duplicateTransactionReference) {
		this.duplicateTransactionReference = duplicateTransactionReference;
	}
	/**
	 * @return the invalidEndBalance
	 */
	public List<CustomerStatementRecord> getInvalidEndBalance() {
		return invalidEndBalance;
	}
	/**
	 * @param invalidEndBalance the invalidEndBalance to set
	 */
	public void setInvalidEndBalance(List<CustomerStatementRecord> invalidEndBalance) {
		this.invalidEndBalance = invalidEndBalance;
	}
	
}
