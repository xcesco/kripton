package bind.kripton81MoreCoverageTests;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean81L {
	
	public long id;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBigDecimal;
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public BigInteger valueBigInteger;
		
}
