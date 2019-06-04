package test.checkout.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.checkout.exception.RepeatedItemException;
import main.checkout.model.Item;
import main.checkout.model.MarketSKUs;
import main.checkout.model.SpecialOffer;

public class MarketSKUsTest {

	private MarketSKUs market;
	private MarketSKUs market2;
	private final static long LONG_12_CURRENCY = 1200;
	private final static long LONG_10_CURRENCY = 1000;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	private final static String ITEM_C = "C";
	
	@Before
	public void setUp() throws Exception {
		market = new MarketSKUs();
		market2 = new MarketSKUs();
	}

	@Test
	public void testAddAndGetItems() throws RepeatedItemException {
		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);
		Item item3 = new Item(ITEM_C, LONG_10_CURRENCY);
		
		market.addItem(item1);
		market.addItem(item2);
		market.addItem(item3);
		
		assertEquals(3, market.getItems().size());
		assertEquals(0, market2.getItems().size());
	}

	
	@Test
	public void testAddRepeatedItemException() {
		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item3 = new Item(ITEM_A, LONG_10_CURRENCY);
		
		try {
			market.addItem(item1);
			market.addItem(item2);
			market.addItem(item3);
		} catch(RepeatedItemException e) {
			assertEquals("This Item is already registered in the Database.", e.getMessage());
		}
	}
	
	@Test
	public void testAddAndGetSpecialOffers() throws RepeatedItemException {
		SpecialOffer offer1 = new SpecialOffer(ITEM_A, 2, LONG_12_CURRENCY);
		SpecialOffer offer2 = new SpecialOffer(ITEM_B, 2, LONG_10_CURRENCY);
		SpecialOffer offer3 = new SpecialOffer(ITEM_C, 2, LONG_10_CURRENCY);
		
		market.addSpecialOffer(offer1);
		market.addSpecialOffer(offer2);
		market.addSpecialOffer(offer3);
		
		assertEquals(3, market.getSpecialOffers().size());
		assertEquals(0, market2.getSpecialOffers().size());
	}

	
	@Test
	public void testAddAndGetSpecialOffersException() {
		SpecialOffer offer1 = new SpecialOffer(ITEM_A, 2, LONG_12_CURRENCY);
		SpecialOffer offer2 = new SpecialOffer(ITEM_B, 2, LONG_10_CURRENCY);
		SpecialOffer offer3 = new SpecialOffer(ITEM_A, 2, LONG_10_CURRENCY);
		

		try {
			market.addSpecialOffer(offer1);
			market.addSpecialOffer(offer2);
			market.addSpecialOffer(offer3);
		} catch(RepeatedItemException e) {
			assertEquals("This Item already have an assigned offer.", e.getMessage());
		}
	}


}
