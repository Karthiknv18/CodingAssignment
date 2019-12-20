package com.rabobank.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rabobank.model.CustomerStatementRecord;
import com.rabobank.model.CustomerStatementResponse;
import com.rabobank.service.Exception.CustomerStatementProcessorException;

/**
 * @author 738575
 *
 */

@Component
public interface CustomerStatementService {

	CustomerStatementResponse processCSVFile(String filePath) throws CustomerStatementProcessorException;
	CustomerStatementResponse processXMLFile(String filePath) throws CustomerStatementProcessorException;
	List<CustomerStatementRecord> findFailedEndBalace(List<CustomerStatementRecord> customerStatementRecordList);
	List<CustomerStatementRecord> addDuplicateTransactionReference(List<CustomerStatementRecord> customerStatementRecordList);
	
}
