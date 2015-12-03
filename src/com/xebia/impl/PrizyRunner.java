package com.xebia.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.xebia.exceptions.IllegalParamException;
/*
 * Main class that provides APIs 
 * to get Product List, 
 * load product details, 
 * view product information
 */
public class PrizyRunner {

	private HashMap<String, Product> prodMap;

	public PrizyRunner() {
		prodMap = new HashMap<String, Product>();
	}

	public PrizyRunner(HashMap<String, Product> map) {
		this.prodMap = map;
	}

	// initialize productList with some dummy data
	public void init() {
		String prodGen = "PRZ";
		Product p = null;
		for (int i = 0; i < 20; i++) {
			p = new Product(prodGen + (100 + i));
			p.setName(p.getBarCode() + "name");
			p.setDescription("New Product with Name:" + p.getName());
			double prizeInit = 100.00;
			for (int j = 0; j < 8; j++) {
				try {
					p.addNewStoreData(new StorePrice("Store" + j, prizeInit
							+ 10 * j));
				} catch (IllegalParamException e) {
					System.out
							.println("There is an exception while initializing Prizyrunner:"
									+ e.getMessage());
				}

			}
			prizeInit += 100;
			this.prodMap.put(p.getBarCode(), p);
		}
		System.out.println("System Initialized with " + this.prodMap.size()
				+ " no of products");
	}

	// Lists products in the repository starting from index to size
	public void getListOfProducts() throws IllegalParamException {
		System.out
				.println("-----------------------Product List------------------------------------");
		int index = 1;
		for (Product p : this.prodMap.values()) {
			System.out.println(index + ". " + p.toString());
			index++;
		}
		System.out
				.println("To select exit Press x... \n To Select any product Enter Barcode...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			input = br.readLine();
			if (input.equalsIgnoreCase("x")) {
				return;
			} else {
				this.viewProduct(input);
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// view the product listed at index

	public void viewProduct(String barcode) throws IllegalParamException {

		Product curProduct = this.prodMap.get(barcode);
		if (curProduct == null) {
			throw new IllegalParamException(
					"No product availble for this barcode:" + barcode);
		}
		System.out
				.println("--------------Product Information-----------------");
		System.out.println("BarCode: " + curProduct.getBarCode());
		System.out.println(" Description: " + curProduct.getDescription());
		System.out.println("Average Price: " + curProduct.getAvgPrice());
		System.out.println("Highest Price: " + curProduct.getMaxPrice());
		System.out.println("Lowest Price: " + curProduct.getMinPrice());
		System.out.println("Ideal Price :" + curProduct.getIdealPrice());
		System.out.println("Number Of PriceCollected: "
				+ curProduct.getNumberOfSamplesCollected());

	}

	// loads the product prize information
	public void loadProduct() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String barCode = null;
		String store = null;
		String notes = null;
		double price = 0;

		try {
			System.out.println("Enter Product Bar:");
			barCode = br.readLine();
			System.out.println("Enter Store Name:");
			store = br.readLine();
			System.out.println("Enter Price:");
			price = Double.parseDouble(br.readLine());
			System.out.println("Enter Notes:");
			notes = br.readLine();
			if (this.prodMap.get(barCode) != null) {
				this.prodMap.get(barCode).addNewStoreData(
						new StorePrice(store, price, notes));
			} else {
				Product newProd = new Product(barCode,
						IdealPriceCalculatorFactory.getCaculator("v1"));
				newProd.addNewStoreData(new StorePrice(store, price, notes));
				this.prodMap.put(barCode, newProd);
			}
			this.viewProduct(barCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		PrizyRunner demoPrizyRunner = new PrizyRunner();
		demoPrizyRunner.init();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Enter Action to be performed:\n 1.Load Product \n 2.View Products \n 3. List Product");
		String input;
		try {
			input = br.readLine();
			int option = Integer.parseInt(input);
			switch (option) {
			case (1):
				demoPrizyRunner.loadProduct();
				break;
			case (2):
				System.out.println("Enter the barcode: ");
				input = br.readLine();
				demoPrizyRunner.viewProduct(input);
				break;
			case (3):
				demoPrizyRunner.getListOfProducts();
			default:

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
