package main.checkout.utils;

import java.util.List;

import main.checkout.model.Item;


public class UtilFunctions {


	public static Item findItemInMarketItemList(final String itemName, List<Item> itemList) {
		return itemList
				.stream()
				.filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElse(null);
	}
}
