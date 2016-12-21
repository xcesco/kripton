package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_3 {
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = BooleanByteArrayTypeAdapter.class, dataType = Long.class)
	public Boolean attributeBoolean;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = BooleanByteArrayTypeAdapter.class, dataType = Long.class)
	public Boolean elementBoolean;

	@BindXml(xmlType = XmlType.VALUE)
	@BindAdapter(adapter = BooleanByteArrayTypeAdapter.class, dataType = Long.class)
	public Boolean dataBoolean;
	
	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = Enum87IntegerTypeAdapter.class, dataType = Integer.class)
	public Enum87A elementEnum;

}
