package sqlite.feature.globaltypeadapters.model1;

import java.sql.Date;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class Date2Long implements SqlTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) {
		if (dataValue != null) {
			return new Date(2);
		}
		return null;
	}

	@Override
	public Long toData(Date javaValue) {
		if (javaValue != null) {
			return javaValue.getTime();
		}
		return null;
	}

	@Override
	public String toString(Date javaValue) {
		return null;
	}

}
