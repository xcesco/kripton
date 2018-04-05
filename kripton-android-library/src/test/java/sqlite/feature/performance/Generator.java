/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.performance;

import java.util.ArrayList;
import java.util.Collection;

import sqlite.feature.performance.simple.SimpleAddressItem;

// TODO: Auto-generated Javadoc
/**
 * The Class Generator.
 */
public class Generator {

	/**
	 * Gets the kripton simple addresses.
	 *
	 * @param itemClass the item class
	 * @param count the count
	 * @return the kripton simple addresses
	 */
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
