package com.xebia.impl;

import java.util.ArrayList;
import java.util.List;

import com.xebia.exceptions.InsufficiantDataException;
import com.xebia.interfaces.IdealPriceCalculatorInf;
import com.xebia.interfaces.ProductInf;

/*
 * product class implementation
 * 
 */
public class Product implements ProductInf {
	private String name;
	private String barCode;
	private String description;
	private IdealPriceCalculatorInf idealPriceCalculator;
	private List<StorePrice> storePriceList;
	private double maxPrice;
	private double minPrice;
	private double sumPrice;

	public Product(String bCode) {
		this.barCode = bCode;
		storePriceList = new ArrayList<StorePrice>();
		idealPriceCalculator = IdealPriceCalculatorFactory.getCaculator("v1");
		maxPrice=0;
		minPrice=0;
		sumPrice=0;
	}

	public Product(String bCode, IdealPriceCalculatorInf iPriceCalculator) {
		this.barCode = bCode;
		this.idealPriceCalculator = iPriceCalculator;
		this.storePriceList = new ArrayList<StorePrice>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String des) {
		this.description = des;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return new StringBuffer("Product:").append(
				this.name + " Avg Price:" + this.getAvgPrice()
						+ " INR Description:" + this.description).toString();
	}

	public String getBarCode() {
		return this.barCode;
	}
	
	public IdealPriceCalculatorInf getIdealPriceCalculator(){
		return this.idealPriceCalculator;
	}
	
	public void setIdealPriceCalculator(
			IdealPriceCalculatorInf idealPriceCalculator) {
		this.idealPriceCalculator = idealPriceCalculator;
	}

	// returns ideal price based of idealPriceCalculator used
	@Override
	public double getIdealPrice() {
		double rslt = 0;
		try {
			rslt = idealPriceCalculator.computeIdealPrice(storePriceList);
		} catch (InsufficiantDataException e) {
			System.out.println("Cannot Compute idealprice because :"
					+ e.getMessage());
		}
		return rslt;
	}

	//returns Avg price of product
	@Override
	public double getAvgPrice() {
		if(this.storePriceList.size()==0)
			return 0;
		return (double) sumPrice / storePriceList.size();
	}

	//returns min price
	@Override
	public double getMinPrice() {
		return this.minPrice;
	}

	//returns max price
	@Override
	public double getMaxPrice() {
		return this.maxPrice;
	}

	// adds new store's price information to product also updates sumPrice also
	// min and max price accordingly
	public void addNewStoreData(StorePrice storePriceObj) {
		this.storePriceList.add(storePriceObj);
		double curPrice = storePriceObj.getPrice();
		if (this.storePriceList.size() == 1) {
			this.minPrice = curPrice;
			this.maxPrice = curPrice;
		} else {
			if (curPrice < minPrice) {
				minPrice = storePriceObj.getPrice();
			}
			if (curPrice > maxPrice) {
				maxPrice = storePriceObj.getPrice();
			}
		}
		this.sumPrice += curPrice;
	}

	//returns number of sample collected to calculate ideal price
	public int getNumberOfSamplesCollected() {
		return this.storePriceList.size();
	}

}
