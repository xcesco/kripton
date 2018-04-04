package sqlite.feature.performance;

import java.util.ArrayList;
import java.util.Collection;

import sqlite.feature.performance.simple.SimpleAddressItem;

public class Generator {

	public static Collection<SimpleAddressItem> getKriptonSimpleAddresses(Class<SimpleAddressItem> itemClass, int count) {
		Collection<SimpleAddressItem> addressItemCollection = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			SimpleAddressItem addressItem = null;
			addressItem = new SimpleAddressItem();
			addressItem.setName("Test");
			addressItem.setAddress("5486 Memory Lane");
			addressItem.setCity("Bronx");
			addressItem.setState("NY");
			addressItem.setPhone(7185555555l);

			addressItemCollection.add(addressItem);
		}
		return addressItemCollection;
	}
}