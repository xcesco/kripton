package bind.kripton81MoreCoverageTests;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.MapEntryType;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81R {
	
	public long id;
	
	@BindXml(mapEntryType=MapEntryType.ATTRIBUTE)
	public Map<String, Integer> valueMapStringInteger;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public byte[] valueByteArray;
	
	@BindXml(xmlType=XmlType.VALUE)
	public Integer valueInteger;
	
}
