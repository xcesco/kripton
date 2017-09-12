package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.BindTypeAdapter;

public class DateAdapterType implements BindTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long toData(Date javaValue) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
