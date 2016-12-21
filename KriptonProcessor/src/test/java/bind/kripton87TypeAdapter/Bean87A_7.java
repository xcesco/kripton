package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_7 {
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = StringInverterTypeAdapter.class, dataType = String.class)
	public String attributeString;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = StringInverterTypeAdapter.class, dataType = String.class)
	public String elementString;

	@BindXml(xmlType = XmlType.VALUE_CDATA)
	@BindAdapter(adapter = StringInverterTypeAdapter.class, dataType = String.class)
	public String dataString;

}
