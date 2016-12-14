package bind.kripton81MoreCoverageTests;

import java.util.Currency;
import java.util.Locale;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81P {
	
	public long id;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Currency valueCurrency;
	
	@BindXml(xmlType=XmlType.VALUE)
	public Locale valueLocale;
	
}
