package bind.kripton81MoreCoverageTests;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.MapEntryType;

@BindType
public class Bean81V4 {
	public long id;
	
	@BindXml(mapEntryType=MapEntryType.TAG)
	public Map<String, Integer> valueMapStringInteger;
	
	public Byte[] valueByteArray;
	
	public List<Integer> valueIntegerList;
			
}
