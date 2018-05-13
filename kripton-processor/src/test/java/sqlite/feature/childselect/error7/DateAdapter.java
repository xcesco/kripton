package sqlite.feature.childselect.error7;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.abubusoft.kripton.TypeAdapter;

public class DateAdapter implements TypeAdapter<Date, String> {

	DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
	
	@Override
	public Date toJava(String dataValue) {
		if (dataValue==null) return null;
		Date date=null;
		try {
			date = formatter.parse(dataValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String toData(Date javaValue) {
		// we don't need to implement for the moment
		return null;
	}

}
