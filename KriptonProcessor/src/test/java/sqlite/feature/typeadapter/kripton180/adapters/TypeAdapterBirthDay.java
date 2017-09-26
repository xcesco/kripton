package sqlite.feature.typeadapter.kripton180.adapters;

import java.sql.Date;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class TypeAdapterBirthDay implements BindSQLTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) {
		if (dataValue!=null) {
			return new Date(dataValue);
		}
		return null;
	}

	@Override
	public Long toData(Date javaValue) {
		if (javaValue!=null) {
			return javaValue.getTime();
		}
		return null;
	}

	@Override
	public String toString(Date javaValue) {
		if (javaValue!=null) {
			return ""+javaValue.getTime();
		}
		return null;
	}

}
