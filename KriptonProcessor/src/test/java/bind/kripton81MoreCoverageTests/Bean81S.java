package bind.kripton81MoreCoverageTests;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.MapEntryType;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean81S {
	
	public long id;
	
	@BindXml(mapEntryType=MapEntryType.ATTRIBUTE, elementTag="map")
	public Map<String, Integer> valueMapStringInteger;
	
	@BindXml(xmlType=XmlType.TAG)
	public byte[] valueByteArray;
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public Integer valueInteger;
	
}
