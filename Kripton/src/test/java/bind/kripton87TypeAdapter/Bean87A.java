package bind.kripton87TypeAdapter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A {
	
	@Bind
	public Date valueDate;
	
	public String valueDescription;	
}
