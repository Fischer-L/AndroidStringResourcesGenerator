package androidStringResources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import unitestLibs.Constants;

public class AndroidQuantityStringTest {

	@Before
	public void setUp() throws Exception {
		this.items = new ArrayList<AndroidQuantityString.AndroidQuantityItem>();
		for (int i = 0; i < this.VALID_QUANTITIES.length; i++) {
			this.items.add(new AndroidQuantityString.AndroidQuantityItem(this.VALID_QUANTITIES[i], this.ITMES[i]));
		}
	}

	@Test
	public void testIsValidQuantity_WithInvalids() {
		String[] invalids = {
			"Zero", "One", "TWO", "FEW", "Many", "Other", "four", "1", "2", "five", "Six"
		};
		for (String q : invalids) {
			assertFalse(AndroidQuantityString.isValidQuantity(q));
		}
	}

	@Ignore
	public void testIsValidQuantity_WithValids() {
		// The testNextItem tests this btw so not testing here.
	}
	
	@Test
	public void testNextItem() {
		int i = 0;
		AndroidQuantityString.AndroidQuantityItem itm;
		AndroidQuantityString qs = new AndroidQuantityString(this.NAME, this.items);
		
		while(
				(itm = qs.nextItem()) != null
		) {
			assertTrue(AndroidQuantityString.isValidQuantity(itm.quantity));
			assertEquals(this.ITMES[i], itm.value);
			i++;
		}
	}
	
	
	/*
	 * Assistive fields
	 **********/

	private String NAME = "programming_lang";
    private final String[] VALID_QUANTITIES = Constants.VALID_QUANTITIES;
	private String[] ITMES = Constants.STRING_VALUES;
	private ArrayList<AndroidQuantityString.AndroidQuantityItem> items;
}
