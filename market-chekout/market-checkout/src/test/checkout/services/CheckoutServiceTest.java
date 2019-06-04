package test.checkout.services;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.checkout.exception.RepeatedItemException;
import main.checkout.model.Cart;
import main.checkout.model.Item;
import main.checkout.model.MarketSKUs;
import main.checkout.model.SpecialOffer;
import main.checkout.services.CheckoutService;
import main.checkout.services.MarketService;

public class CheckoutServiceTest {
	
	private final static long LONG_12_CURRENCY = 1200;
	private final static long LONG_10_CURRENCY = 1000;
	private final static long LONG_5_CURRENCY = 500;
	private final static long LONG_7_CURRENCY = 700;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	private final static String ITEM_C = "C";
	private final static String ITEM_D = "D";
	
	
	@Test
	public void testCalculateDiscountNoDiscount() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Map<String, Long> informationSpecialOffers = new HashMap<String, Long>();

		Item item1 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item3 = new Item(ITEM_A, LONG_10_CURRENCY);
		
		List<Item> items = new ArrayList<Item>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		
		List<SpecialOffer> special = new ArrayList<SpecialOffer>();
		special.add(new SpecialOffer(ITEM_A, 3, LONG_5_CURRENCY));
		
		Method methodCalc = CheckoutService.class.getDeclaredMethod("calculateDiscount" , List.class, List.class, Map.class);
		methodCalc.setAccessible(true);
		long resultCalculation = (long) methodCalc.invoke(null, items, special, informationSpecialOffers);

		assertEquals(0, resultCalculation);
	}
	
	@Test
	public void testCalculateDiscount() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Map<String, Long> informationSpecialOffers = new HashMap<String, Long>();

		Item item1 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item3 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item4 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item5 = new Item(ITEM_A, LONG_10_CURRENCY);
		
		List<Item> items = new ArrayList<Item>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		
		List<SpecialOffer> special = new ArrayList<SpecialOffer>();
		special.add(new SpecialOffer(ITEM_A, 3, LONG_5_CURRENCY));
		
		Method methodCalc = CheckoutService.class.getDeclaredMethod("calculateDiscount" , List.class, List.class, Map.class);
		methodCalc.setAccessible(true);
		long resultCalculation = (long) methodCalc.invoke(null, items, special, informationSpecialOffers);

		assertEquals(LONG_5_CURRENCY, resultCalculation);
	}
	
	@Test
	public void testCalculateDiscountMultipleTimes() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Map<String, Long> informationSpecialOffers = new HashMap<String, Long>();

		Item item1 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item3 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item4 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item5 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item6 = new Item(ITEM_A, LONG_10_CURRENCY);
		Item item7 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item8 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item9 = new Item(ITEM_C, LONG_5_CURRENCY);
		
		List<Item> items = new ArrayList<Item>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		items.add(item8);
		items.add(item9);
		
		List<SpecialOffer> special = new ArrayList<SpecialOffer>();
		special.add(new SpecialOffer(ITEM_A, 2, LONG_5_CURRENCY));
		special.add(new SpecialOffer(ITEM_B, 3, LONG_7_CURRENCY));
		
		Method methodCalc = CheckoutService.class.getDeclaredMethod("calculateDiscount" , List.class, List.class, Map.class);
		methodCalc.setAccessible(true);
		long resultCalculation = (long) methodCalc.invoke(null, items, special, informationSpecialOffers);

		assertEquals((LONG_7_CURRENCY+(2*LONG_5_CURRENCY)), resultCalculation);
		
		assertTrue(informationSpecialOffers.containsKey("A 2 item(s)"));
		assertTrue(informationSpecialOffers.containsKey("B 3 item(s)"));
		assertEquals(2, (long) informationSpecialOffers.get("A 2 item(s)"));
	}

	@Test
	public void testAddItemToCart() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);
		Item item3 = new Item(ITEM_C, LONG_10_CURRENCY);
		Item item4 = new Item(ITEM_D, LONG_10_CURRENCY);			
		MarketSKUs market = new MarketSKUs();
		market.addItem(item1);
		market.addItem(item2);
		market.addItem(item3);
		market.addItem(item4);
		
		String input = "A\n"
				+ "B\n"
				+ "C\n"
				+ "B\n"
				+ "B\n"
				+ "finish\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		Cart cart = new Cart();
		
		CheckoutService.addItemToCart(cart, market.getItems());

		assertEquals(5, cart.getItems().size());
	}
	
	@Test
	public void testAddItemToCartInvalidProduct() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);
		Item item3 = new Item(ITEM_C, LONG_10_CURRENCY);
		Item item4 = new Item(ITEM_D, LONG_10_CURRENCY);
		
		MarketSKUs market = new MarketSKUs();
		
		market.addItem(item1);
		market.addItem(item2);
		market.addItem(item3);
		market.addItem(item4);
		market.addSpecialOffer(new SpecialOffer(ITEM_A,3,LONG_5_CURRENCY));
		
		String input = "A\n"
				+ "Invalid\n"
				+ "Invalid\n"
				+ "B\n"
				+ "B\n"
				+ "finish\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		Cart cart = new Cart();
		
		CheckoutService.addItemToCart(cart, market.getItems());

		assertEquals(3, cart.getItems().size());
	}

	@Test
	public void testCheckoutCart() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);
		Item item3 = new Item(ITEM_C, LONG_10_CURRENCY);
		Item item4 = new Item(ITEM_D, LONG_10_CURRENCY);
		
		MarketSKUs market = new MarketSKUs();
		
		market.addItem(item1);
		market.addItem(item2);
		market.addItem(item3);
		market.addItem(item4);
		market.addSpecialOffer(new SpecialOffer(ITEM_A,3,LONG_5_CURRENCY));

		Cart cart = new Cart();
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item1);
		cart.addItem(item1);
		cart.addItem(item3);
		
		CheckoutService.checkoutCart(cart, market.getSpecialOffers());

		assertEquals(5, cart.getItems().size());

	}
}
