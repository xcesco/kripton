package bind.kripton87TypeAdapter;

import java.math.BigDecimal;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_5 {
	
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = BooleanBigDecimalTypeAdapter.class, dataType = BigDecimal.class)
	public Boolean attributeBoolean;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = BooleanBigDecimalTypeAdapter.class, dataType = BigDecimal.class)
	public Boolean elementBoolean;

	@BindXml(xmlType = XmlType.VALUE)
	@BindAdapter(adapter = BooleanBigDecimalTypeAdapter.class, dataType = BigDecimal.class)
	public Boolean dataBoolean;

}
