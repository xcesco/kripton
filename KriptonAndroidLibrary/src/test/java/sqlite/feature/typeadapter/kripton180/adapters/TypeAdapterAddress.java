package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

import sqlite.feature.typeadapter.kripton180.Address;

public class TypeAdapterAddress implements BindSQLTypeAdapter<Address, String> {

	@Override
	public Address toJava(String dataValue) {
		if ("0000".equals(dataValue)) {
			Address address= new Address();
			address.city="city0";
			address.street="street0";
		}
		return null;
	}

	@Override
	public String toData(Address javaValue) {
		if (javaValue==null) {
			return "0000";
		}
		return null;
	}

	@Override
	public String toString(Address javaValue) {
		if (javaValue==null) {
			return "0000";
		}
		return null;
	}

}
