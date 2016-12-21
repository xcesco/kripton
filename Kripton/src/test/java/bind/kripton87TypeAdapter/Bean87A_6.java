package bind.kripton87TypeAdapter;

import java.net.URL;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_6 {
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = StringUrlTypeAdapter.class, dataType = URL.class)
	public String attributeString;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = StringUrlTypeAdapter.class, dataType = URL.class)
	public String elementString;

	@BindXml(xmlType = XmlType.VALUE)
	@BindAdapter(adapter = StringUrlTypeAdapter.class, dataType = URL.class)
	public String dataString;

}
