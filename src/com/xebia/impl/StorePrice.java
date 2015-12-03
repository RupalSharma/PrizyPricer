package com.xebia.impl;

import com.xebia.exceptions.IllegalParamException;
import com.xebia.interfaces.StorePriceInf;
/*
 * Class to store Mapping of Store to price information of a products
 */
public class StorePrice implements Comparable<StorePrice>, StorePriceInf {
	private String storeName;
	private double price;
	private String notes;

	public StorePrice(String name, double price) throws IllegalParamException {
		if (this.price < 0) {
			throw new IllegalParamException(
					"price of a product can not be negative");
		}
		this.storeName = name;
		this.price = price;
		this.notes = null;
	}

	public StorePrice() {
	}

	public StorePrice(String store, double price, String notes)throws IllegalParamException {
		if (this.price < 0) {
			throw new IllegalParamException(
					"price of a product can not be negative");
		}
		this.storeName = store;
		this.price = price;
		this.notes = notes;
	}

	public void setStoreName(String name) {
		this.storeName = name;
	}

	public void setPrice(double p) throws IllegalParamException {
		if(p<0){
			throw new IllegalParamException("Price can not be negative value");
		}
		this.price = p;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public double getPrice() {
		return this.price;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int compareTo(StorePrice o) {
		if (o.price > this.price) {
			return -1;
		} else if (this.price > o.price) {
			return 1;
		} else {
			return 0;
		}
	}

}
