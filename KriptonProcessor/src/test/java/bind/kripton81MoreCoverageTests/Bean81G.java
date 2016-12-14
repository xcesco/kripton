package bind.kripton81MoreCoverageTests;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81G {
	
	public long id;
	
	@BindXml(xmlType=XmlType.VALUE)
	public BigDecimal valueBigDecimal;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBigInteger;
	
}
