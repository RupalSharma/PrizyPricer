package com.xebia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xebia.exceptions.IllegalParamException;
import com.xebia.impl.Product;
import com.xebia.impl.StorePrice;

public class ProductTest {
	
	Product testObj;
	@Before
	public void setUp() throws Exception {
		testObj = new Product("TESTOBJ1111");
		testObj.setDescription("this is a test Object");
		testObj.setName("test Object");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProductinit() throws IllegalParamException {
		testObj.setDescription("this is a test Object");
		testObj.setName("test Object");
		
		assertNotNull(testObj.getIdealPriceCalculator());
		assertEquals(testObj.getNumberOfSamplesCollected(),0);
	
		testObj.addNewStoreData(new StorePrice("s1", 300));
		testObj.addNewStoreData(new StorePrice("s2", 400));
		testObj.addNewStoreData(new StorePrice("s3", 500));
		
		assertEquals(testObj.getNumberOfSamplesCollected(), 3);
		
	}
	
	@Test
	public void testAvgPrice() throws IllegalParamException{
		assertEquals(0,testObj.getAvgPrice(),0.01);
		testObj.addNewStoreData(new StorePrice("s1", 300));
		testObj.addNewStoreData(new StorePrice("s2", 400));
		testObj.addNewStoreData(new StorePrice("s3", 500));
		assertEquals((double)(300+400+500)/3, testObj.getAvgPrice(),0.00);
	}
	
	@Test
	public void testMinMaxPrice() throws IllegalParamException{
		assertEquals(0, testObj.getMinPrice(),0.00);
		testObj.addNewStoreData(new StorePrice("s1", 300));
		testObj.addNewStoreData(new StorePrice("s2", 400));
		testObj.addNewStoreData(new StorePrice("s3", 500));
		assertEquals(300, testObj.getMinPrice(),0.00);
		assertNotEquals(400, testObj.getMinPrice(),0.00);
		assertEquals(500, testObj.getMaxPrice(),0.00);
		assertNotEquals(400, testObj.getMaxPrice(),0.00);
	}
}
