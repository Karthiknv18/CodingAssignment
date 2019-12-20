package com.rabobank.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opencsv.CSVReader;
import com.rabobank.model.CustomerStatementRecord;
import com.rabobank.model.CustomerStatementResponse;
import com.rabobank.service.CustomerStatementService;
import com.rabobank.service.Exception.CustomerStatementProcessorException;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerStatementServiceImpl.class);

	@Autowired
	CustomerStatementResponse customerStatementResponse;

	@Override
	public CustomerStatementResponse processCSVFile(String csvFilePath) throws CustomerStatementProcessorException {
		List<CustomerStatementRecord> customerStatementRecordList = new ArrayList<>();
		CustomerStatementRecord customerStatementRecord = null;
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFilePath));
			String[] line;
			reader.readNext();
			while ((line = reader.readNext()) != null) {
				customerStatementRecord = new CustomerStatementRecord(Long.parseLong(line[0]), line[1], line[2],
						new BigDecimal(line[3]), new BigDecimal(line[4]), new BigDecimal(line[5]));
				customerStatementRecordList.add(customerStatementRecord);
			}
			builldCustomerStatementResponse(customerStatementRecordList);

		} catch (IOException e) {
			logger.error("Error in Processing your CSV file" , e.getMessage());
			throw new CustomerStatementProcessorException("Error in Processing your CSV file");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error("Error in Processing your CSV file" , e.getMessage());
				throw new CustomerStatementProcessorException("Error in Processing your CSV file");
			}
		}
		return customerStatementResponse;
	}

	@Override
	public CustomerStatementResponse processXMLFile(String csvFilePath) throws CustomerStatementProcessorException {
		List<CustomerStatementRecord> customerStatementRecordList = new ArrayList<>();
		CustomerStatementRecord customerStatementRecord = null;
		try {
			File fXmlFile = new File(csvFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("record");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					customerStatementRecord = new CustomerStatementRecord(
							Long.parseLong(eElement.getAttribute("reference")),
							eElement.getElementsByTagName("accountNumber").item(0).getTextContent(),
							eElement.getElementsByTagName("description").item(0).getTextContent(),
							new BigDecimal(eElement.getElementsByTagName("startBalance").item(0).getTextContent()),
							new BigDecimal(eElement.getElementsByTagName("mutation").item(0).getTextContent()),
							new BigDecimal(eElement.getElementsByTagName("endBalance").item(0).getTextContent()));
					customerStatementRecordList.add(customerStatementRecord);
				}
			}

		} catch (Exception e) {
			logger.error("Error in Processing your XML file" , e.getMessage());
			throw new CustomerStatementProcessorException("Error in Processing your XML file");
		}
		builldCustomerStatementResponse(customerStatementRecordList);
		return customerStatementResponse;
	}

	@Override
	public List<CustomerStatementRecord> findFailedEndBalace(List<CustomerStatementRecord> customerStatementRecordList) {	
		return customerStatementRecordList.stream()
				.filter(p -> !p.getStartingBalance().add(p.getMutation()).equals(p.getEndBalance()))
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerStatementRecord> addDuplicateTransactionReference(List<CustomerStatementRecord> customerStatementRecordList) {
		return customerStatementRecordList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > 1L).map(e -> e.getKey()).collect(Collectors.toList());
		
	}

	private void builldCustomerStatementResponse(List<CustomerStatementRecord> customerStatementRecordList) {
		customerStatementResponse.setDuplicateTransactionReference(addDuplicateTransactionReference(customerStatementRecordList));
		customerStatementResponse.setInvalidEndBalance(findFailedEndBalace(customerStatementRecordList));
	}

}
