package com.rabobank.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabobank.Constants.FileFormat;
import com.rabobank.model.CustomerStatementRequest;
import com.rabobank.model.CustomerStatementResponse;
import com.rabobank.service.CustomerStatementService;
import com.rabobank.service.Exception.CustomerStatementProcessorException;

/**
 * @author 738575
 *
 */
@RestController
public class CustomerStatementProcessorController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerStatementProcessorController.class);

	@Autowired
	CustomerStatementService customerStatementService;

	@Autowired
	CustomerStatementResponse customerStatementResponse;

	@PostMapping("/statementProcessor")
	public CustomerStatementResponse statementProcessor(@RequestBody CustomerStatementRequest customerStatementRequest)	throws Exception {
		String filePath = customerStatementRequest.getPath();
		logger.debug("CustomerStatementProcessorController :: statementProcessor - Started");
		if (!StringUtils.isEmpty(filePath)) {
			logger.debug("CustomerStatementProcessorController :: statementProcessor - File Format " , filePath.substring(filePath.length() - 3));
			if (filePath.substring(filePath.length() - 3).equalsIgnoreCase(FileFormat.XML.toString()))
				customerStatementResponse = customerStatementService.processXMLFile(filePath);
			else if (filePath.substring(filePath.length() - 3).equalsIgnoreCase(FileFormat.CSV.toString()))
				customerStatementResponse = customerStatementService.processCSVFile(filePath);
			else
				throw new CustomerStatementProcessorException("Invalid file format Provided");
		} else
			throw new CustomerStatementProcessorException("Input not Provided");

		logger.debug("CustomerStatementProcessorController :: statementProcessor - Completed");
		return customerStatementResponse;
	}

}
