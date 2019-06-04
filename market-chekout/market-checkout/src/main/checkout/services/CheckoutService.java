package main.checkout.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.checkout.model.Cart;
import main.checkout.model.Item;
import main.checkout.model.SpecialOffer;
import main.checkout.ui.CheckoutUI;


public class CheckoutService {

	
	public static void addItemToCart(Cart cart, List<Item> stockItems) {

		CheckoutUI.addItemsToCart(cart, stockItems);

	}
	
	
	public static void checkoutCart(Cart cart, List<SpecialOffer> specialOffers) {

		Map<String, Long> informationSpecialOffers = new HashMap<String, Long>();
		long valueToReduce = calculateDiscount(cart.getItems(), specialOffers, informationSpecialOffers);
		
		CheckoutUI.displayCheckout(cart, specialOffers, informationSpecialOffers, valueToReduce);

	}

	
	private static long calculateDiscount(List<Item> items, List<SpecialOffer> specialOffers, Map<String, Long> informationSpecialOffers) {

		long valueToReduce = 0;

		for(SpecialOffer specialDiscount : specialOffers) {
			long quantityItems = items
					.stream()
					.filter(item -> item.getName().equals(specialDiscount.getItemName()))
					.count();

			long timesToApplyDiscount = quantityItems / specialDiscount.getRequiredNumberOfProducts();

			if(timesToApplyDiscount > 0) {				
				long discountToApply = specialDiscount.getSpecialPriceDiscount() * timesToApplyDiscount;
			
				valueToReduce += discountToApply;
	
				informationSpecialOffers.put(specialDiscount.getItemName() + " " 
						+ specialDiscount.getRequiredNumberOfProducts() + " item(s)",
						timesToApplyDiscount);
			}
		};
		
		return valueToReduce;

	}
}
