package com.xebia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xebia.exceptions.IllegalParamException;
import com.xebia.impl.StorePrice;

public class StorePriceTest {

	StorePrice testObj;

	@Before
	public void setUp() throws Exception {
		testObj = new StorePrice();
	}

	@After
	public void tearDown() throws Exception {
		testObj = null;
	}

	@Test
	public void testStorePrieInit() {
		StorePrice obj = null;
		try {
			obj = new StorePrice("test1", -5.00);
		} catch (IllegalParamException e) {
			assertNull("Object can not be initialized", obj);
		}

	}

	@Test
	public void testComapreStorePrice() throws IllegalParamException {
		StorePrice obj = new StorePrice("test1", 300);
		testObj.setPrice(300.00);
		assertEquals(obj.getPrice(), testObj.getPrice(), 0.01);
		testObj.setPrice(340.00);
		assertTrue(obj.getPrice() < testObj.getPrice());
	}

}
