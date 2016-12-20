package bind.kripton87TypeAdapter;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean87A {
	
	@BindAdapter(adapter=DateTypeAdapter.class, dataType=Long.class)	
	public Date valueDate;
	
	public String valueDescription;	
}
