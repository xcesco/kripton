package bind.kripton87TypeAdapter;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean87A_1 {
	
	@BindAdapter(adapter=DateLongTypeAdapter.class, dataType=Long.class)	
	public Date valueDate;
	
	@BindAdapter(adapter=StringInverterTypeAdapter.class, dataType=String.class)
	public String valueDescription;	
}
