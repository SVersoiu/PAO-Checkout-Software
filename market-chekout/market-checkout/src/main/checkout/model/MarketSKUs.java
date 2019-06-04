package main.checkout.model;

import java.util.ArrayList;
import java.util.List;

import main.checkout.exception.RepeatedItemException;


public class MarketSKUs {

	private List<Item> items;
	private List<SpecialOffer> specialOffers;
	
	public MarketSKUs() {
		this.items = new ArrayList<Item>();
		this.specialOffers = new ArrayList<SpecialOffer>();
	}
	
	
	public void addItem(Item item) throws RepeatedItemException {
		boolean isRepeated = items
				.stream()
				.anyMatch(existingItem -> item.getName().equals(existingItem.getName()));
		if(isRepeated) {
			throw new RepeatedItemException("This Item is already registered in the Database.");
		}
		this.items.add(item);
	}
	

	public List<Item> getItems() {
		return this.items;
	}
	
	
	public void addSpecialOffer(SpecialOffer specialOffer) throws RepeatedItemException {
		boolean isRepeated = specialOffers
				.stream()
				.anyMatch(offer -> specialOffer.getItemName().equals(offer.getItemName()));
		if(isRepeated) {
			throw new RepeatedItemException("This Item already have an assigned offer.");
		}
		this.specialOffers.add(specialOffer);
	}
	

	public List<SpecialOffer> getSpecialOffers() {
		return this.specialOffers;
	}
}
