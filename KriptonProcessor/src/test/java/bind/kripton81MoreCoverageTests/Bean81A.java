package bind.kripton81MoreCoverageTests;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81A {
	
	public long id;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Bean81Enum valueEnum;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBidDecimal;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBidInteger;
	
}
