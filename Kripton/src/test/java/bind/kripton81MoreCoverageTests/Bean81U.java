package bind.kripton81MoreCoverageTests;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.MapEntryType;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean81U {
	public long id;
	
	@BindXml(mapEntryType=MapEntryType.TAG, elementTag="map")
	public Map<String, Integer> valueMapStringInteger;
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public byte[] valueByteArray;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueInteger;
			
}
