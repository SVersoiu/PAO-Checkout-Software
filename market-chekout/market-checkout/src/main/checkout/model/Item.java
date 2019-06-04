package main.checkout.model;


public class Item {
	
	private String name;
	private long value;
	
	public Item(String name, long value) {
		this.name = name;
		this.value = value;
	}
	

	public String getName() {
		return this.name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public long getValue() {
		return this.value;
	}

	
	public void setValue(long value) {
		this.value = value;
	}

}
