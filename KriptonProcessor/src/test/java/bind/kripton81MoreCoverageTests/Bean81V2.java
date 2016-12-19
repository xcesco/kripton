package bind.kripton81MoreCoverageTests;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.MapEntryType;

@BindType
public class Bean81V2 {
	public long id;
	
	@Bind("map")
	@BindXml(mapEntryType=MapEntryType.TAG)
	public Map<String, Integer> valueMapStringInteger;
	
	@Bind("array")	
	public Byte[] valueByteArray;
	
	@Bind("list")
	public List<Integer> valueIntegerList;
			
}
