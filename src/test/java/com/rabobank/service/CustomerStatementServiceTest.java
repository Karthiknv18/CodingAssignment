package com.rabobank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabobank.model.CustomerStatementRecord;

@SpringBootTest
public class CustomerStatementServiceTest {
	
	@Autowired
	CustomerStatementService customerStatementService;
	
	List<CustomerStatementRecord> customerStatementRecordList = new ArrayList<CustomerStatementRecord>();
	
	@Before
	private List<CustomerStatementRecord> setup() {		
		CustomerStatementRecord customerStatementRecord1 = new CustomerStatementRecord(177666, "NL93ABNA0585619023", "Flowers for Rik Theu",new BigDecimal(44.85), new BigDecimal(-22.24), new BigDecimal(22.61));
		CustomerStatementRecord customerStatementRecord2  = new CustomerStatementRecord(112806, "NL69ABNA0433647324", "Subscription for Jan Theu", new BigDecimal(45.59), new BigDecimal(48.18), new BigDecimal(93.77));		
		customerStatementRecordList.add(customerStatementRecord1);
		customerStatementRecordList.add(customerStatementRecord2);
		return customerStatementRecordList;
	}

    @Test
    public void findFailedEndBalaceTest() {		
		customerStatementService.findFailedEndBalace(customerStatementRecordList);
    }

    @Test
    public void addDuplicateTransactionReferenceTest() {		
		customerStatementService.addDuplicateTransactionReference(customerStatementRecordList);
    }
	

}