package sqlite.feature.in.case5;

import java.sql.Date;

import com.abubusoft.kripton.android.SqlTypeAdapter;
import com.abubusoft.kripton.common.DateUtils;

public class DateAdapter implements SqlTypeAdapter<Date, String> {

	@Override
	public Date toJava(String dataValue) {
		if (dataValue==null) return null;
		return new Date(DateUtils.read(dataValue).getTime());		
	}

	@Override
	public String toData(Date javaValue) {
		if (javaValue==null) return null;
		return DateUtils.writeShort(javaValue);
	}

	@Override
	public String toString(Date javaValue) {
		if (javaValue==null) return null;
		return toData(javaValue);
	}

}
