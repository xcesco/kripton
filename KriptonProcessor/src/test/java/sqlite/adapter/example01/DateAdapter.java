package sqlite.adapter.example01;

import java.util.Date;

import com.abubusoft.kripton.BindTypeAdapter;

public class DateAdapter implements BindTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) throws Exception {
		if (dataValue==null) return null;
		
		return new Date(dataValue);
	}

	@Override
	public Long toData(Date javaValue) throws Exception {
		if (javaValue!=null) return javaValue.getTime();
		
		return null;
	}
	


}
