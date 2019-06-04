package main.checkout.services;

import main.checkout.model.MarketSKUs;
import main.checkout.ui.CheckoutUI;

public class MarketService {

	
	public static void addItemsToMarket(MarketSKUs market) {
		
		CheckoutUI.addItemsToMarketStock(market);
		
	}
	
	public static void addSpecialPricesToMarket(MarketSKUs market) {

		CheckoutUI.addSpecialPricesToMarket(market);

	}
	
}
