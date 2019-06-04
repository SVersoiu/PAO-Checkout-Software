package test.checkout.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import main.checkout.model.MarketSKUs;
import main.checkout.utils.UtilDefaultDefinitions;

public class UtilDefaultDefinitionsTest {

	@Test
	public void testSetDefaultSKUs() {
		MarketSKUs market = new MarketSKUs();
		UtilDefaultDefinitions.setDefaultSKUs(market);

	    assertEquals(4, market.getItems().size());
	    assertEquals(2, market.getSpecialOffers().size());
	}

}
