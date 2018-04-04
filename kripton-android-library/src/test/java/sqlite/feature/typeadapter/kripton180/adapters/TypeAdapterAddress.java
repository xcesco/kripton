package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.android.SqlTypeAdapter;

import sqlite.feature.typeadapter.kripton180.Address;

public class TypeAdapterAddress implements SqlTypeAdapter<Address, String> {

	@Override
	public Address toJava(String dataValue) {
		if (dataValue!=null) {
			return KriptonBinder.jsonBind().parse(dataValue, Address.class);
		}
		return null;
	}

	@Override
	public String toData(Address javaValue) {
		if (javaValue!=null) {
			return KriptonBinder.jsonBind().serialize(javaValue);
		}
		return null;
	}

	@Override
	public String toString(Address javaValue) {
		if (javaValue!=null) {
			return KriptonBinder.jsonBind().serialize(javaValue);
		}
		return null;
	}

}
