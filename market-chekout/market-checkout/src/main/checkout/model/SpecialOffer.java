package main.checkout.model;


public class SpecialOffer {
	
	private String itemName;
	private int requiredNumberOfProducts;
	private long specialPriceDiscount;
	
	public SpecialOffer(String itemName, int requiredNumber, long discount) {
		this.itemName = itemName;
		this.requiredNumberOfProducts = requiredNumber;
		this.specialPriceDiscount = discount;
	}


	public String getItemName() {
		return this.itemName;
	}


	public int getRequiredNumberOfProducts() {
		return this.requiredNumberOfProducts;
	}

	public long getSpecialPriceDiscount() {
		return this.specialPriceDiscount;
	}	
}
