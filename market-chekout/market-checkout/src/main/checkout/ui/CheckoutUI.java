package main.checkout.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.checkout.exception.RepeatedItemException;
import main.checkout.model.Cart;
import main.checkout.model.Item;
import main.checkout.model.MarketSKUs;
import main.checkout.model.SpecialOffer;
import main.checkout.utils.UtilFunctions;


public class CheckoutUI {

	private static Scanner scan;

	public static void addItemsToMarketStock(MarketSKUs market) {

		String sName = null;
		String sValue = null;
		BigDecimal nValue;
		
		System.out.println("-- Add new Items --");
		System.out.println("To stop adding items, type \"next\".");

		scan = new Scanner(System.in);

		mainLoop:
			while(true) {
				nValue = null;
				System.out.println("Type the name of the Item:");
				sName = scan.next();
				if("next".equals(sName.toLowerCase())) {
					break mainLoop;
				}
				do {
					System.out.println("Type the value of the Item:");
					sValue = scan.next();
					if ("next".equals(sValue.toLowerCase())) {
						break mainLoop;
					}
					try {
						nValue = new BigDecimal(sValue).movePointRight(2);					
					} catch(Exception e) {
						System.out.println("Invalid number, try again...");
					}
				} while (nValue == null);

				try {
					market.addItem(new Item(sName, nValue.longValue()));
					System.out.println("Added " + sName + " - £" + sValue);
				} catch (RepeatedItemException e) {
					System.out.println(e.getMessage());
					System.out.println("Try again...");
				}
			}
	}

	
	public static void addSpecialPricesToMarket(MarketSKUs market) {

		String sName;
		String sAmount;
		Integer nAmount;
		String sValue;
		BigDecimal nValue;
		
		System.out.println("-- Add new Special prices --");
		System.out.println("SKUs:");
		market.getItems()
			.stream()
			.forEach(item -> System.out.println(item.getName()));
		System.out.println("To stop adding special prices, type \"next\".");
		
		scan = new Scanner(System.in);

		mainLoop:
			while(true) {

				sName = null;
				sAmount = null;
				nAmount = null;
				sValue = null;
				nValue = null;
				do {
					System.out.println("Type the name of the Item:");
					sName = scan.next();
					if ("next".equals(sName.toLowerCase())) {
						break mainLoop;
					}
					Item foundItem = UtilFunctions
							.findItemInMarketItemList(sName, market.getItems());
					if (foundItem == null) {
						System.out.println("Item not found, try again...");
						sName = null;
					}
				} while(sName == null);

				do {
					System.out.println("Type the number of required Items for the special price:");
					sAmount = scan.next();
					if ("next".equals(sAmount.toLowerCase())) {
						break mainLoop;
					}
					try {
						nAmount = Integer.parseInt(sAmount);					
					}catch(Exception e) {
						System.out.println("Invalid amount number, try again...");
					}
				} while (nAmount == null);

				do {
					System.out.println("Type the value of discount when " + nAmount + " items are added to the cart:");
					sValue = scan.next();
					if ("next".equals(sValue.toLowerCase())) {
						break mainLoop;
					}
					try {
						nValue = new BigDecimal(sValue).movePointRight(2);					
					} catch(Exception e) {
						System.out.println("Invalid number, try again...");
					}
				} while(nValue == null);
				
				try {
					market.addSpecialOffer(new SpecialOffer(sName, nAmount, nValue.longValue()));
					System.out.println("Special Offer Added to " + sName);
				} catch (RepeatedItemException e) {
					System.out.println(e.getMessage());
					System.out.println("Try again...");
				}
			}
	}
	
	
	public static void addItemsToCart(Cart cart, List<Item> stockItems) {

		String sName;

		System.out.println("-- Add new Items to Cart --");
		System.out.println("SKUs:");
		stockItems
			.stream()
			.forEach(item -> System.out.println(item.getName()));
		System.out.println("To stop adding items to Cart and Checkout it, type \"finish\".");

		scan = new Scanner(System.in);

		mainLoop:
			while(true) {

				sName = null;
				Item foundItem = null;
				do {
					System.out.println("Type the name of the Item:");
					sName = scan.next();
					if ("finish".equals(sName.toLowerCase())) {
						break mainLoop;
					}
					foundItem = UtilFunctions
							.findItemInMarketItemList(sName, stockItems);
					if (foundItem == null) {
						System.out.println("Item not found, try again...");
					}
				} while(foundItem == null);

				cart.addItem(foundItem);
				System.out.println(sName + " added to cart.");
			}
	}

	public static void displayCheckout(Cart cart, List<SpecialOffer> specialOffers, Map<String, Long> informationSpecialOffers, long valueToReduce) {

		System.out.println("---- CHECKOUT ----");
		System.out.println("Items:");
		cart.getItems()
			.stream()
			.forEach(item -> System.out.println(item.getName() + " £" + new BigDecimal(item.getValue()).movePointLeft(2)));
		System.out.println("--------");
		
		System.out.println("Special Prices included:");
		informationSpecialOffers
			.forEach((key, value) -> System.out.println(key + " - applied " + value + " times."));
		System.out.println("--------");
		
		System.out.println("Total to pay:");
		System.out.println("Original value £" + new BigDecimal(cart.getTotalValue()).movePointLeft(2));
		System.out.println("Discount add value £" + new BigDecimal(valueToReduce).movePointLeft(2));
		System.out.println("--------");
		System.out.println("Final TOTAL Price £" + new BigDecimal(cart.getTotalValue() - valueToReduce).movePointLeft(2));
		System.out.println("--------");

	}
}
