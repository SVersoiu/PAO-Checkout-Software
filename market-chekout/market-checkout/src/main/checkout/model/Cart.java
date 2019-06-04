package main.checkout.model;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	
	private List<Item> items;
	private long totalValue;
	
	public Cart() {
		this.items = new ArrayList<Item>();
		this.totalValue = 0;
	}
	
	
	public void addItem(Item item) {
		this.items.add(item);
		this.totalValue += item.getValue();
	}
	
	
	public List<Item> getItems() {
		return this.items;
	}


	public long getTotalValue() {
		return this.totalValue;
	}
}
