package test.checkout.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.checkout.model.Item;
import main.checkout.utils.UtilFunctions;

public class UtilFunctionsTest {

	@Test
	public void testFindItemInMarketItemListNotFound() {
		
		List<Item> items = new ArrayList<Item>();
		
		items.add(new Item("A", 1000));
		items.add(new Item("B", 1100));
		items.add(new Item("C", 1200));
		
		assertNull(UtilFunctions.findItemInMarketItemList("N", items));
	}
	
	@Test
	public void testFindItemInMarketItemListFound() {
		
		List<Item> items = new ArrayList<Item>();
		
		String nameItemA = "A";
		Item itemA = new Item(nameItemA, 1000);
		
		items.add(itemA);
		items.add(new Item("B", 1100));
		items.add(new Item("C", 1200));
		
		assertEquals(itemA, UtilFunctions.findItemInMarketItemList(nameItemA, items));
	}

}
