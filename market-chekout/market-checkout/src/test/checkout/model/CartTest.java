package test.checkout.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.checkout.exception.RepeatedItemException;
import main.checkout.model.Cart;
import main.checkout.model.Item;

public class CartTest {

	private Cart cart1;
	private Cart cart2;
	private final static long LONG_12_CURRENCY = 1200;
	private final static long LONG_10_CURRENCY = 1000;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	private final static String ITEM_C = "C";
	
	@Before
	public void setUp() throws Exception {
		cart1 = new Cart();
		cart2 = new Cart();
	}

	@Test
	public void testAddAndGetItems() {
		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_10_CURRENCY);
		Item item3 = new Item(ITEM_C, LONG_10_CURRENCY);
		
		cart1.addItem(item1);
		cart1.addItem(item2);
		cart1.addItem(item3);
		
		assertEquals(3, cart1.getItems().size());
		assertEquals(0, cart2.getItems().size());
	}

	@Test
	public void testGetTotalValue() {
		Item item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		Item item2 = new Item(ITEM_B, LONG_12_CURRENCY);
		Item item3 = new Item(ITEM_C, LONG_10_CURRENCY);
		
		cart1.addItem(item1);
		cart1.addItem(item2);
		cart1.addItem(item3);
		
		long expectedValue = LONG_12_CURRENCY 
				+ LONG_12_CURRENCY 
				+ LONG_10_CURRENCY;
		
		assertEquals(expectedValue, cart1.getTotalValue());
	}


}
