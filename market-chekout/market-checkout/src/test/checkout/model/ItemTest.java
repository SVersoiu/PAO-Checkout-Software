package test.checkout.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.checkout.model.Item;

public class ItemTest {
	
	private Item item1;
	private Item item2;
	private final static long LONG_12_CURRENCY = 1200;
	private final static long LONG_10_CURRENCY = 1000;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	
	@Before
	public void setUp() throws Exception {
		item1 = new Item(ITEM_A, LONG_12_CURRENCY);
		item2 = new Item(ITEM_B, LONG_10_CURRENCY);
	}
	
	@Test
	public void testGetName() {

		assertEquals(ITEM_A, item1.getName());
		assertEquals(ITEM_B, item2.getName());
		assertNotEquals("C", item2.getName());

	}

	@Test
	public void testSetName() {

		item1.setName("C");
		assertEquals("C", item1.getName());
	
	}

	@Test
	public void testGetValue() {

		assertEquals(LONG_12_CURRENCY, item1.getValue());
		assertEquals(LONG_10_CURRENCY, item2.getValue());
		assertNotEquals(5000, item2.getValue());

	}

	@Test
	public void testSetValue() {

		item1.setValue(5000);
		assertEquals(5000, item1.getValue());

	}

}
