package main.checkout;

import main.checkout.model.Cart;
import main.checkout.model.MarketSKUs;
import main.checkout.services.CheckoutService;
import main.checkout.services.MarketService;
import main.checkout.utils.UtilDefaultDefinitions;

public class CheckoutMain {

	public static void main(String[] args) {

		MarketSKUs market = new MarketSKUs();
		
		UtilDefaultDefinitions.setDefaultSKUs(market);
		
		MarketService.addItemsToMarket(market);
		
		MarketService.addSpecialPricesToMarket(market);
		
		Cart cart = new Cart();

		CheckoutService.addItemToCart(cart, market.getItems());

		CheckoutService.checkoutCart(cart, market.getSpecialOffers());

	}

}
