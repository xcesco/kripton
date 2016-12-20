package bind.kripton87TypeAdapter;

import java.util.Date;

import com.abubusoft.kripton.BindTypeAdapter;

public class DateTypeAdapter implements BindTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) {
		return new Date(dataValue);
	}

	@Override
	public Long toData(Date javaValue) {
		return javaValue.getTime();
	}

}
