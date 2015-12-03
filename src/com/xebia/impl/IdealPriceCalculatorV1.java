package com.xebia.impl;

import java.util.Collections;
import java.util.List;

import com.xebia.exceptions.InsufficiantDataException;
import com.xebia.interfaces.IdealPriceCalculatorInf;
/*
 * Ideal Price calculator version1:
 * This price is calculated by taking all the prices of this product,
 * removing the 2 highest and 2 lowest, then doing an average with the rest 
 * and adding 20% to it.
 */
public class IdealPriceCalculatorV1 implements IdealPriceCalculatorInf {
	
	private final String version= "v1";
	@Override
	
	//calculates idealPrice for product and returns double value
	public double computeIdealPrice(List<StorePrice> prodList)
			throws InsufficiantDataException {
		Collections.sort(prodList);
		int len = prodList.size();
		if (len <= 4) {
			throw new InsufficiantDataException(
					"Insufficient Data to compute ideal price");
		}
		double sumPrice = 0;
		for (int i = 2; i < len - 2; i++) {
			sumPrice += prodList.get(i).getPrice();
		}

		double rslt = (double) sumPrice / (len - 4);

		return (double)rslt*1.2;
	}
	
	public String getVersion(){
		return version;
	}

}
