package com.xebia.impl;

import com.xebia.interfaces.IdealPriceCalculatorInf;

/*
 * Factory class for creating IdealPriceCalculator based on versions
 * 
 */
public class IdealPriceCalculatorFactory {

	public static IdealPriceCalculatorInf getCaculator(String version) {

		if (version.equalsIgnoreCase("v1")) {
			return new IdealPriceCalculatorV1();
		} else if (version.equalsIgnoreCase("v2")) {
			return new IdealPriceCalculatorV2();
		} else {
			return null;
		}
	}
}
