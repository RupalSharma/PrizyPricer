package com.xebia.interfaces;

import java.util.List;

import com.xebia.exceptions.InsufficiantDataException;
import com.xebia.impl.StorePrice;

public interface IdealPriceCalculatorInf {

	public double computeIdealPrice(List<StorePrice> prodList)
			throws InsufficiantDataException;
	public String getVersion();

}
