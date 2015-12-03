package com.xebia.impl;

import java.util.List;

import com.xebia.interfaces.IdealPriceCalculatorInf;

/*
 * its dummy class just to specify that depending on different formula we can 
 * have different version of ideal price calculator
 */
public class IdealPriceCalculatorV2 implements IdealPriceCalculatorInf {

	private final String version= "v2";
	@Override
	public double computeIdealPrice(List<StorePrice> prodList) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getVersion(){
		return version;
	}

}
