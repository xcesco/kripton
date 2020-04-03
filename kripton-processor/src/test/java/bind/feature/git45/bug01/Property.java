package bind.feature.git45.bug01;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Property {
	public String content;

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String name;

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String value;
}