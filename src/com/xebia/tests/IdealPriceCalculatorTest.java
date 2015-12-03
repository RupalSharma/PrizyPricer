package com.xebia.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xebia.exceptions.IllegalParamException;
import com.xebia.exceptions.InsufficiantDataException;
import com.xebia.impl.IdealPriceCalculatorFactory;
import com.xebia.impl.IdealPriceCalculatorV1;
import com.xebia.impl.Product;
import com.xebia.impl.StorePrice;
import com.xebia.interfaces.IdealPriceCalculatorInf;

public class IdealPriceCalculatorTest {

	public Product pTest;
	public IdealPriceCalculatorV1 ipcTest;

	@Before
	public void setUp() throws Exception {
		pTest = new Product("BRZ111DEMO");
		ipcTest = new IdealPriceCalculatorV1();

	}

	@After
	public void tearDown() throws Exception {
		pTest = null;
		ipcTest = null;
	}

	@Test
	public void testIdeaPriceCalculatorVersion() {
		assertNotNull(pTest.getIdealPriceCalculator());
		assertEquals(pTest.getIdealPriceCalculator().getVersion(), "v1");

	}

	@Test
	public void testIdealPriceCalculatorVersionChange() {
		IdealPriceCalculatorInf id = IdealPriceCalculatorFactory
				.getCaculator("v2");
		pTest.setIdealPriceCalculator(id);
		assertNotEquals(pTest.getIdealPriceCalculator().getVersion(), "v1");
	}

	@Test(expected = InsufficiantDataException.class)
	public void testIdealPriceCalculation1() throws InsufficiantDataException {
		ArrayList<StorePrice> sp = new ArrayList<StorePrice>();
		try {
			sp.add(new StorePrice("s1", 300));
			sp.add(new StorePrice("s2", 328.50));
			sp.add(new StorePrice("s3", 330.00));
		} catch (Exception e) {

		}

		ipcTest.computeIdealPrice(sp);
	}

	@Test
	public void testIdealPriceCalculation2() throws InsufficiantDataException,
			IllegalParamException {
		ArrayList<StorePrice> sp = new ArrayList<StorePrice>();

		sp.add(new StorePrice("s1", 300));
		sp.add(new StorePrice("s2", 328.50));
		sp.add(new StorePrice("s3", 330.00));
		sp.add(new StorePrice("s4", 332.00));
		sp.add(new StorePrice("s4", 335.00));
		assertEquals(330.00 * 1.2, ipcTest.computeIdealPrice(sp), 0.10);

	}

}
