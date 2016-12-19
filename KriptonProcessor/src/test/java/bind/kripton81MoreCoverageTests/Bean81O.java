package bind.kripton81MoreCoverageTests;

import java.util.Currency;
import java.util.Locale;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean81O {
	
	public long id;
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public Currency valueCurrency;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Locale valueLocale;
	
}
