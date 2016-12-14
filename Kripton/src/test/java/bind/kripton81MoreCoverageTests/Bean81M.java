package bind.kripton81MoreCoverageTests;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81M {
	
	public long id;

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString1;
	
	@BindXml(xmlType=XmlType.VALUE)
	public String valueString2;
	
}
