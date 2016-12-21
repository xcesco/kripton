package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_8 {
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = StringEnum87ATypeAdapter.class, dataType = Enum87A.class)
	public String attributeString;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = StringEnum87ATypeAdapter.class, dataType = Enum87A.class)
	public String elementString;

	@BindXml(xmlType = XmlType.VALUE_CDATA)
	@BindAdapter(adapter = StringEnum87ATypeAdapter.class, dataType = Enum87A.class)
	public String dataString;

}
