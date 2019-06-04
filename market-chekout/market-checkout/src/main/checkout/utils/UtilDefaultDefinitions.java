package main.checkout.utils;

import main.checkout.exception.RepeatedItemException;
import main.checkout.model.Item;
import main.checkout.model.MarketSKUs;
import main.checkout.model.SpecialOffer;


public class UtilDefaultDefinitions {

	private final static long LONG_50_CURRENCY = 5000;
	private final static long LONG_30_CURRENCY = 3000;
	private final static long LONG_20_CURRENCY = 2000;
	private final static long LONG_15_CURRENCY = 1500;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	private final static String ITEM_C = "C";
	private final static String ITEM_D = "D";
	
	
	public static void setDefaultSKUs(MarketSKUs market) {
		
		
		try {

			market.addItem(new Item(ITEM_A, LONG_50_CURRENCY));
			market.addItem(new Item(ITEM_B, LONG_30_CURRENCY));
			market.addItem(new Item(ITEM_C, LONG_20_CURRENCY));
			market.addItem(new Item(ITEM_D, LONG_15_CURRENCY));

			market.addSpecialOffer(new SpecialOffer(ITEM_A, 3, LONG_20_CURRENCY));
			market.addSpecialOffer(new SpecialOffer(ITEM_B, 2, LONG_15_CURRENCY));

		} catch (RepeatedItemException e) {
			e.printStackTrace();
		}
		
	}
}
