package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.BindTypeAdapter;

public class DateAdapterType implements BindTypeAdapter<Date, Long> {

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

}
