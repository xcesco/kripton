package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_4 {
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = BooleanBigDecimalTypeAdapter.class, dataType = Long.class)
	public boolean attributeBoolean;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = BooleanBigDecimalTypeAdapter.class, dataType = Long.class)
	public boolean elementBoolean;

	@BindXml(xmlType = XmlType.VALUE)
	@BindAdapter(adapter = BooleanBigDecimalTypeAdapter.class, dataType = Long.class)
	public boolean dataBoolean;

}
