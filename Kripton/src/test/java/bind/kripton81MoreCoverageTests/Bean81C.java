package bind.kripton81MoreCoverageTests;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean81C {
	
	public long id;
	
	@BindXml(xmlType=XmlType.VALUE)
	public Bean81Enum valueEnum;
		
}
