package test.checkout.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.checkout.model.SpecialOffer;

public class SpecialOfferTest {

	private SpecialOffer offer1;
	private SpecialOffer offer2;
	private final static long LONG_12_CURRENCY = 1200;
	private final static long LONG_10_CURRENCY = 1000;
	private final static String ITEM_A = "A";
	private final static String ITEM_B = "B";
	
	@Before
	public void setUp() throws Exception {
		offer1 = new SpecialOffer(ITEM_A, 2, LONG_12_CURRENCY);
		offer2 = new SpecialOffer(ITEM_B, 2, LONG_10_CURRENCY);
	}
	
	@Test
	public void testGetName() {

		assertEquals(ITEM_A, offer1.getItemName());
		assertEquals(ITEM_B, offer2.getItemName());
		assertNotEquals("C", offer1.getItemName());

	}

	@Test
	public void testGetRequiredNumberOfProducts() {
		assertEquals(2, offer1.getRequiredNumberOfProducts());
		assertEquals(2, offer2.getRequiredNumberOfProducts());
		assertNotEquals(5, offer1.getRequiredNumberOfProducts());
	}

	@Test
	public void testGetSpecialPriceDiscount() {

		assertEquals(LONG_12_CURRENCY, offer1.getSpecialPriceDiscount());
		assertEquals(LONG_10_CURRENCY, offer2.getSpecialPriceDiscount());
		assertNotEquals(5000, offer1.getSpecialPriceDiscount());
	}

}
