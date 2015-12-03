package com.xebia.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IdealPriceCalculatorTest.class, ProductTest.class,
		StorePriceTest.class })
public class XebiaAllTests {

}
