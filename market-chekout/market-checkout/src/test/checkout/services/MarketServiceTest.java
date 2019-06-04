package test.checkout.services;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.junit.Test;

import main.checkout.exception.RepeatedItemException;
import main.checkout.model.Item;
import main.checkout.model.MarketSKUs;
import main.checkout.services.MarketService;

public class MarketServiceTest {

	private final static long LONG_12_CURRENCY = 1200;
	private final static long LONG_10_CURRENCY = 1000;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	
	@Test
	public void testAddItemsToMarket() {
		String input = "A\n" 
				+ "10\n"
				+ "B\n"
				+ "20.50\n"
				+ "next\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketSKUs market = new MarketSKUs();

		MarketService.addItemsToMarket(market);

		assertEquals(2, market.getItems().size());
	}

	@Test
	public void testAddItemsToMarketInvalidInputsNoItems() {
		String input = "A\n" 
				+ "aa\n"
				+ "B\n"
				+ "aaa\n"
				+ "next\n";


		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketSKUs market = new MarketSKUs();

		MarketService.addItemsToMarket(market);

		assertEquals(0, market.getItems().size());
	}

	@Test
	public void testAddItemsToMarketRepeatedItems() {
		String input = "A\n" 
				+ "12\n"
				+ "A\n"
				+ "10\n"
				+ "next\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketSKUs market = new MarketSKUs();

		MarketService.addItemsToMarket(market);

		assertEquals(1, market.getItems().size());
	}


	@Test
	public void testAddSpecialPricesToMarket() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);		
		MarketSKUs market = new MarketSKUs();
		market.addItem(item1);
		market.addItem(item2);
		
		String input = "A\n"
				+ "2\n"
				+ "10\n"
				+ "B\n"
				+ "3\n"
				+ "2.50\n"
				+ "next\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketService.addSpecialPricesToMarket(market);

		assertEquals(2, market.getSpecialOffers().size());
	}
	
	@Test
	public void testAddSpecialPricesToMarketRepeatedItems() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);		
		MarketSKUs market = new MarketSKUs();
		market.addItem(item1);
		market.addItem(item2);
		
		String input = "A\n"
				+ "2\n"
				+ "10\n"
				+ "A\n"
				+ "3\n"
				+ "2.50\n"
				+ "next\n";


		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketService.addSpecialPricesToMarket(market);

		assertEquals(1, market.getSpecialOffers().size());
	}
	
	@Test
	public void testAddSpecialPricesInvalidInputs() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);		
		MarketSKUs market = new MarketSKUs();
		market.addItem(item1);
		market.addItem(item2);
		
		String input = "A\n"
				+ "aaa\n"
				+ "2\n"
				+ "bbb\n"
				+ "10\n"
				+ "B\n"
				+ "3\n"
				+ "2.50\n"
				+ "invalidItem\n"
				+ "next\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketService.addSpecialPricesToMarket(market);

		assertEquals(2, market.getSpecialOffers().size());
	}
	
	@Test
	public void testAddSpecialPricesInvalidQuitOnAmount() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);		
		MarketSKUs market = new MarketSKUs();
		market.addItem(item1);
		market.addItem(item2);
		
		String input = "A\n"
				+ "2\n"
				+ "10\n"
				+ "B\n"
				+ "next\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketService.addSpecialPricesToMarket(market);

		assertEquals(1, market.getSpecialOffers().size());
	}

	@Test
	public void testAddSpecialPricesInvalidQuitOnDiscountValue() throws RepeatedItemException {

		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);		
		MarketSKUs market = new MarketSKUs();
		market.addItem(item1);
		market.addItem(item2);
		
		String input = "A\n"
				+ "2\n"
				+ "10\n"
				+ "B\n"
				+ "2\n"
				+ "next\n";

		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		MarketService.addSpecialPricesToMarket(market);

		assertEquals(1, market.getSpecialOffers().size());
	}

}
