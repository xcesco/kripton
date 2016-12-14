package bind.kripton81MoreCoverageTests;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81N {
	
	public long id;

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString1;
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public String valueString2;
		
}
